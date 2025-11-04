package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.ui.viewmodel.PizzaTimeViewModel

@Composable
fun PantallaListarPedidos(
    modifier: Modifier = Modifier,
    pizzaTimeViewModel: PizzaTimeViewModel = viewModel()
) {
    val pizzaTimeUIState by pizzaTimeViewModel.uiState.collectAsState()

    // Lista de pedidos del UIState
    val pedidos = pizzaTimeUIState.listaPedidos

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de pedidos",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}