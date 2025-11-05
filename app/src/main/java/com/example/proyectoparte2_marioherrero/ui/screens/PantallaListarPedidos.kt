package com.example.proyectoparte2_marioherrero.ui.screens

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.ui.viewmodel.PizzaTimeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaListarPedidos(
    modifier: Modifier = Modifier,
    pizzaTimeViewModel: PizzaTimeViewModel = viewModel()
) {
    val pizzaTimeUIState by pizzaTimeViewModel.uiState.collectAsState()

    val usuario = pizzaTimeUIState.usuarioActual
    val pedidos = pizzaTimeUIState.listaPedidos

    LaunchedEffect(usuario.id) {
        pizzaTimeViewModel.cargarPedidosUsuario(usuario.id)
    }

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
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pedidos) { pedido ->
                TarjetaPedido(pedido)
            }
        }
    }
}

@Composable
fun InfoPedido(
    icon: Int,
    texto: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = texto
        )
    }
}

@Composable
fun TarjetaPedido(
    pedido: Pedido,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoPedido(R.drawable.pizza, "${pedido.cantidadPizza} x Pizza ${pedido.pizza.nombre} (${pedido.pizza.tamaño})")
            InfoPedido(R.drawable.bebida, "${pedido.cantidadBebida} x ${pedido.bebida.tipoBebida}")
            InfoPedido(R.drawable.dinero, "${pedido.precio}")
            InfoPedido(R.drawable.calendario, "${pedido.fecha}")
        }
    }
}

