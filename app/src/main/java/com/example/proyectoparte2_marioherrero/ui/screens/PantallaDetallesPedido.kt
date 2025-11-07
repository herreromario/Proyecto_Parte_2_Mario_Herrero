package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.PizzaBarbacoa
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.PizzaRomana
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState

@Composable
fun PantallaDetallesPedido(
    modifier: Modifier = Modifier,
    pizzaTimeUIState: PizzaTimeUIState,
) {
    val pedido = pizzaTimeUIState.pedidoSeleccionado

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
        TarjetaDetallesGenerales(pedido)
    }
}

@Composable
fun TarjetaDetallesGenerales(
    pedido: Pedido?,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        if(pedido == null){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.cargando_detalles)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Pedido #${pedido?.id}"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Realizado el día ${pedido?.fecha}"
                    )
                }
            }
        }
    }
    Spacer(modifier = modifier.height(10.dp))
    TarjetaDetallesPedido(pedido)
}

@Composable
fun TarjetaDetallesPedido(
    pedido: Pedido?,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InfoPedido(R.drawable.pizza, "${pedido?.cantidadPizza} x Pizza ${pedido?.pizza?.nombre} de tamaño ${pedido?.pizza?.tamaño}")
            when(pedido?.pizza){
                is PizzaBarbacoa -> {
                    InfoPedido(R.drawable.carne, "Con carne de ${(pedido.pizza.carne)}")
                }

                is PizzaMargarita -> {
                    if(pedido.pizza.piña){
                        InfoPedido(R.drawable.pi_a, "Con piña")
                    } else {
                        InfoPedido(R.drawable.cruz, "Sin piña")
                    }

                    if (pedido.pizza.vegana){
                        InfoPedido(R.drawable.planta, "Opción vegana")
                    }
                }

                is PizzaRomana -> {
                    if(pedido.pizza.champiñones){
                        InfoPedido(R.drawable.champi_on, "Con champiñones")
                    } else{
                        InfoPedido(R.drawable.cruz, "Sin champiñones")
                    }

                }
            }
        }
    }
}