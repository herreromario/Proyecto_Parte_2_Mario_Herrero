package com.example.proyectoparte2_marioherrero.ui.screens

import android.os.Build
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.AmarilloQuesoLight
import com.example.proyectoparte2_marioherrero.ui.theme.RojoTomateLight

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaListarPedidos(
    onBotonDetallesPulsado: (Pedido) -> Unit,
    modifier: Modifier = Modifier,
    pizzaTimeUIState: PizzaTimeUIState
) {

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
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pedidos) { pedido ->
                TarjetaPedido(
                    pedido = pedido,
                    onBotonDetallesPulsado = onBotonDetallesPulsado
                )
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
    onBotonDetallesPulsado: (Pedido) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoPedido(R.drawable.pizza, "${pedido.cantidadPizza} x Pizza ${pedido.pizza.nombre} (${pedido.pizza.tamaño})")
            if(pedido.cantidadBebida > 0){
                InfoPedido(R.drawable.bebida, "${pedido.cantidadBebida} x ${pedido.bebida.tipoBebida}")
            } else {
                InfoPedido(R.drawable.bebida, stringResource(R.string.sin_bebida))
            }
            InfoPedido(R.drawable.dinero, "${pedido.precio} €")
            InfoPedido(R.drawable.calendario, "${pedido.fecha}")
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { onBotonDetallesPulsado(pedido) },
                    colors = ButtonDefaults.buttonColors(RojoTomateLight),
                ) {
                    Text(
                        text = "Detalles"
                    )
                }
            }
        }
    }
}

