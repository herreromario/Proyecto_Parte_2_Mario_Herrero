package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.datos.listaPizzasDisponibles
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Pizza
import com.example.proyectoparte2_marioherrero.modelo.PizzaBarbacoa
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.PizzaRomana
import com.example.proyectoparte2_marioherrero.modelo.TamañoPizza
import com.example.proyectoparte2_marioherrero.modelo.TipoCarne
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.AmarilloQuesoLight
import com.example.proyectoparte2_marioherrero.ui.theme.RojoTomateLight


@Composable
fun PantallaRealizarPedido(
    modifier: Modifier = Modifier,
    onPizzaSelecionada: (Pizza) -> Unit,
    onPiñaPulsado: () -> Unit,
    onVeganoPulsado: () -> Unit,
    onChampiñonesPulsado: () -> Unit,
    onCarneSeleccionada: (TipoCarne) -> Unit,
    onTamañoSeleccionado: (TamañoPizza) -> Unit,
    onAumentarCantidadPizza: () -> Unit,
    onDisminuirCantidadPizza: () -> Unit,
    onBebidaSeleccionada: (tipoBebida) -> Unit,
    onAumentarCantidadBebida: () -> Unit,
    onDisminuirCantidadBebida: () -> Unit,
    onProcecerPago: () -> Unit,
    pizzaTimeUIState: PizzaTimeUIState,

){
    val pedidoActual = pizzaTimeUIState.pedidoActual

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.realizar_un_pedido),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElegirPizza(
            onPizzaSelecionada = onPizzaSelecionada,
            pedidoActual = pedidoActual
        )

        ElegirExtras(
            pedidoActual = pedidoActual,
            onPiñaPulsado = onPiñaPulsado,
            onVeganoPulsado = onVeganoPulsado,
            onChampiñonesPulsado = onChampiñonesPulsado,
            onCarneSeleccionada = onCarneSeleccionada
            )
        ElegirTamaño(
            pedidoActual = pedidoActual,
            onTamañoSeleccionado = onTamañoSeleccionado
        )
        CambiarCantidadPizza(
            pedidoActual = pedidoActual,
            onAumentarCantidadPizza = onAumentarCantidadPizza,
            onDisminuirCantidadPizza = onDisminuirCantidadPizza
        )

        ElegirBebida(
            pedidoActual = pedidoActual,
            onBebidaSeleccionada = onBebidaSeleccionada
        )
        CambiarCantidadBebida(
            pedidoActual = pedidoActual,
            onAumentarCantidadBebida = onAumentarCantidadBebida,
            onDisminuirCantidadBebida = onDisminuirCantidadBebida
        )
        PrecioProcederPago(
            pedidoActual = pedidoActual,
            onClick = {onProcecerPago()}
        )
    }
}

@Composable
fun ElegirPizza(
    modifier: Modifier = Modifier,
    onPizzaSelecionada: (Pizza) -> Unit,
    pedidoActual: Pedido?
){
    SeccionTitulo(stringResource(R.string.selecciona_una_pizza))

    Column(modifier = Modifier
        .padding(vertical = 8.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listaPizzasDisponibles.forEach { pizza ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (pedidoActual?.pizza?.nombre == pizza.nombre) AmarilloQuesoLight else MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onPizzaSelecionada(pizza) }
                        .padding(8.dp),
                ){
                    Text(
                        text = pizza.nombre
                    )
                }
            }
        }
    }
}

@Composable
fun ElegirExtras(
    pedidoActual: Pedido?,
    onPiñaPulsado: () -> Unit,
    onVeganoPulsado: () -> Unit,
    onChampiñonesPulsado: () -> Unit,
    onCarneSeleccionada: (TipoCarne) -> Unit,
    modifier: Modifier = Modifier
) {
    var abierto by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Personalizar Pizza ${pedidoActual?.pizza?.nombre}")
                TarjetaBoton(
                    abierto = abierto,
                    onClick = { abierto = !abierto }
                )
            }
            if (abierto) {
                MostrarExtras(
                    pedidoActual = pedidoActual,
                    onPiñaPulsado = onPiñaPulsado,
                    onVeganoPulsado = onVeganoPulsado,
                    onChampiñonesPulsado = onChampiñonesPulsado,
                    onCarneSeleccionada = onCarneSeleccionada
                )
            }
        }
    }
}

@Composable
fun MostrarExtras(
    pedidoActual: Pedido?,
    onPiñaPulsado: () -> Unit,
    onVeganoPulsado: () -> Unit,
    onChampiñonesPulsado: () -> Unit,
    onCarneSeleccionada: (TipoCarne) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        when (val pizza = pedidoActual?.pizza) {
            is PizzaMargarita -> {
                CambiarIngrediente(
                    etiqueta = "piña",
                    checked = pizza.piña,
                    onCheckedChange = { onPiñaPulsado() }
                )
                CambiarIngrediente(
                    etiqueta = "opción vegana",
                    checked = pizza.vegana,
                    onCheckedChange = { onVeganoPulsado() }
                )
            }
            is PizzaRomana -> {
                CambiarIngrediente(
                    etiqueta = "champiñones",
                    checked = pizza.champiñones,
                    onCheckedChange = { onChampiñonesPulsado() }
                )
            }

            is PizzaBarbacoa -> {
                CambiarCarne(
                    pedidoActual = pedidoActual,
                    onCarneSeleccionada = onCarneSeleccionada
                )
            }

            else -> {}
        }
    }
}

@Composable
fun CambiarIngrediente(
    etiqueta: String,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        IconToggleButton(
            checked = checked,
            onCheckedChange = { onCheckedChange() }
        ) {
            Icon(
                imageVector = if (checked) Icons.Filled.CheckCircle else Icons.Filled.Add,
                contentDescription = if (checked) "Con $etiqueta" else "Sin $etiqueta"
            )
        }
        Text(
            text = if (checked) stringResource(R.string.con, etiqueta) else stringResource(R.string.sin, etiqueta)
        ) }
}

@Composable
fun TarjetaBoton(
    abierto: Boolean,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (abierto) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = if (abierto) "cerrar" else "abrir"
        )
    }
}

@Composable
fun CambiarCarne(
    modifier: Modifier = Modifier,
    pedidoActual: Pedido?,
    onCarneSeleccionada: (TipoCarne) -> Unit
) {
    val pizza = pedidoActual?.pizza
    if (pizza !is PizzaBarbacoa) return

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(
            modifier = Modifier
                .padding(bottom = 4.dp)
        ) {
            Text(
                stringResource(R.string.selecciona_el_tipo_de_carne),
                fontWeight =  FontWeight.SemiBold
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TipoCarne.values().forEach { carne ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (pizza.carne == carne) AmarilloQuesoLight else MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onCarneSeleccionada(carne) }
                        .padding(8.dp),
                ){
                    Text(
                        text = carne.name
                    )
                }
            }
        }
    }
}

@Composable
fun ElegirTamaño(
    modifier: Modifier = Modifier,
    pedidoActual: Pedido?,
    onTamañoSeleccionado: (TamañoPizza) -> Unit
){
    SeccionTitulo(stringResource(R.string.selecciona_un_tama_o))

    Column(modifier = Modifier
        .padding(vertical = 8.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TamañoPizza.values().forEach { tamaño ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (pedidoActual?.pizza?.tamaño == tamaño) AmarilloQuesoLight else MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onTamañoSeleccionado(tamaño) }
                        .padding(8.dp),
                ){
                    Text(
                        text = tamaño.name
                    )
                }
            }
        }
    }
}

@Composable
fun CambiarCantidadPizza(
    modifier: Modifier = Modifier,
    onAumentarCantidadPizza: () -> Unit,
    onDisminuirCantidadPizza: () -> Unit,
    pedidoActual: Pedido?,
){
    SeccionTitulo(stringResource(R.string.cantidad_de_pizzas))

    ContadorCantidad(
        cantidad = pedidoActual?.cantidadPizza ?: return,
        onIncrementar = { onAumentarCantidadPizza() },
        onDecrementar = { onDisminuirCantidadPizza() },
    )
}

@Composable
fun ElegirBebida(
    modifier: Modifier = Modifier,
    onBebidaSeleccionada: (tipoBebida) -> Unit,
    pedidoActual: Pedido?
){
    SeccionTitulo(stringResource(R.string.selecciona_una_bebida))

    Column(modifier = Modifier
        .padding(vertical = 8.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tipoBebida.values().forEach { bebida ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (pedidoActual?.bebida?.tipoBebida == bebida) AmarilloQuesoLight else MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onBebidaSeleccionada(bebida) }
                        .padding(8.dp)
                ){
                    Text(
                        text = bebida.name
                    )
                }
            }
        }
    }
}

@Composable
fun CambiarCantidadBebida(
    modifier: Modifier = Modifier,
    onAumentarCantidadBebida: () -> Unit,
    onDisminuirCantidadBebida: () -> Unit,
    pedidoActual: Pedido?
){
    val bebida = pedidoActual?.bebida?.tipoBebida

    if(bebida != tipoBebida.SIN_BEBIDA){
        SeccionTitulo(stringResource(R.string.cantidad_de_bebidas))

        ContadorCantidad(
            cantidad = pedidoActual?.cantidadBebida ?: return,
            onIncrementar = { onAumentarCantidadBebida() },
            onDecrementar = { onDisminuirCantidadBebida() }
        )
    }
}

@Composable
fun ContadorCantidad(
    cantidad: Int,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botón de restar
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { onDecrementar() }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "−",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // Valor actual
        Text(
            text = "${cantidad}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        // Botón de sumar
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { onIncrementar() }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun PrecioProcederPago(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    pedidoActual: Pedido?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.precio_2f).format(pedidoActual?.precio ?: 0.0),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
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
                    text = stringResource(R.string.proceder_con_el_pago),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}