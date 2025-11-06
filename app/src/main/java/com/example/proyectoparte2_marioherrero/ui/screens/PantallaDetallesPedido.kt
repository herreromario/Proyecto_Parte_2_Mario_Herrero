package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState

@Composable
fun PantallaDetallesPedido(
    modifier: Modifier = Modifier,
    pizzaTimeUIState: PizzaTimeUIState
) {
    val pedido = pizzaTimeUIState.detallePedido

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detalles del pedido",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}