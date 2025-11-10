package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.tipoTarjeta
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.AmarilloQuesoLight
import com.example.proyectoparte2_marioherrero.ui.theme.RojoTomateLight

@Composable
fun PantallaFormularioPago(
    modifier: Modifier = Modifier,
    onTarjetaSeleccionada: (tipoTarjeta) -> Unit,
    onCambiarNumeroTarjeta: (String) -> Unit,
    onCambiarFechaCaducidad: (String) -> Unit,
    onCambiarCodigoSeguridad: (String) -> Unit,
    onPagarPulsado: () -> Unit,
    onVolverAtrasPulsado: () -> Unit,
    pizzaTimeUIState: PizzaTimeUIState
){
    val pedidoActual = pizzaTimeUIState.pedidoActual

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.formulario_de_pago),
            style = MaterialTheme.typography.headlineLarge
        )
        ElegirTipoTarjeta(
            pedidoActual = pedidoActual,
            onTarjetaSeleccionada = onTarjetaSeleccionada
        )
        CambiarNumeroTarjeta(
            pedidoActual = pedidoActual,
            onCambiarNumeroTarjeta = onCambiarNumeroTarjeta
        )
        CambiarFechaYCodigoSeguridad(
            pedidoActual = pedidoActual,
            onCambiarFechaCaducidad = onCambiarFechaCaducidad,
            onCambiarCodigoSeguridad = onCambiarCodigoSeguridad
        )
        PagarPedido(
            pedidoActual = pedidoActual,
            onClick = { onPagarPulsado() }
        )
        VolverAtras(
            onClick = { onVolverAtrasPulsado() }
        )
    }
}

@Composable
fun ElegirTipoTarjeta(
    modifier: Modifier = Modifier,
    onTarjetaSeleccionada: (tipoTarjeta) -> Unit,
    pedidoActual: Pedido?
){
    SeccionTitulo(stringResource(R.string.selecciona_un_tipo_de_tarjeta))

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            tipoTarjeta.values().forEach { tarjeta ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (pedidoActual?.pago?.tipoTarjeta == tarjeta) AmarilloQuesoLight else MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onTarjetaSeleccionada(tarjeta) }
                        .padding(8.dp)
                ){
                    Text(
                        text = tarjeta.name
                    )
                }
            }
        }
    }
}

@Composable
fun CambiarNumeroTarjeta(
    modifier: Modifier = Modifier,
    onCambiarNumeroTarjeta: (String) -> Unit,
    pedidoActual: Pedido?,

){
    SeccionTitulo(stringResource(R.string.numero_de_tarjeta))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        value = pedidoActual?.pago?.numeroTarjeta ?: "",
        onValueChange = { input ->
            val filtrado = input.filter { it.isDigit() }.take(16)
            onCambiarNumeroTarjeta(filtrado)
        },
        placeholder = { Text(text = "0000 0000 0000 0000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(stringResource(R.string.numero_de_la_tarjeta)) }
    )
}

@Composable
fun CambiarFechaYCodigoSeguridad(
    pedidoActual: Pedido?,
    onCambiarFechaCaducidad: (String) -> Unit,
    onCambiarCodigoSeguridad: (String) -> Unit
){
    SeccionTitulo(stringResource(R.string.fecha_de_caducidad_cvc))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Fecha de caducidad
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = pedidoActual?.pago?.fechaCaducidad ?: "",
            onValueChange = { input ->
                val filtrado = input.filter { it.isDigit() || it == '/' }
                val final = filtrado.take(5)
                onCambiarFechaCaducidad(final)
            },
            placeholder = { Text(text = "MM/AA") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.fecha)) }
        )

        // Código de seguridad
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = pedidoActual?.pago?.cvc ?: "",
            onValueChange = { input ->
                val filtrado = input.filter { it.isDigit() }.take(3)
                onCambiarCodigoSeguridad(filtrado)
            },
            placeholder = { Text(text = "123") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("CVC") }
        )
    }
}

@Composable
fun PagarPedido(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    pedidoActual: Pedido?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
    ) {
        Text(
            text = stringResource(id = R.string.precio_2f).format(pedidoActual?.precio ?: 0.0),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = RojoTomateLight),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .size(width = 250.dp, height = 70.dp)
            ) {
                Text(
                    text = stringResource(R.string.pagar_y_finalizar),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun VolverAtras(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = RojoTomateLight),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(20.dp)
                .size(width = 250.dp, height = 70.dp)
        ) {
            Text(
                text = stringResource(R.string.volver_atras),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}