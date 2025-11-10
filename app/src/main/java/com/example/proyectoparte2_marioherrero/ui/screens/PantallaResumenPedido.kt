package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.RojoTomateLight

@Composable
fun PantallaResumenPedido(
    modifier: Modifier = Modifier,
    onFinalizarPedido: () -> Unit,
    pizzaTimeUIState: PizzaTimeUIState
) {
    val pedido = pizzaTimeUIState.pedidoActual
    val usuario = pizzaTimeUIState.usuarioActual

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.resumen_del_pedido),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        TarjetaDetallesGenerales(pedido = pedido, usuario = usuario)

        FinalizarPedido(
            onClick = { onFinalizarPedido() }
        )
    }
}

@Composable
fun FinalizarPedido(
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
                text = stringResource(R.string.finalizar_pedido),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

