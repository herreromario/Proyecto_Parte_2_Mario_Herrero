package com.example.proyectoparte2_marioherrero.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
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
            text = stringResource(R.string.lista_de_pedidos),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
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

// Función InfoPedido reutilizable
@Composable
fun InfoPedido(
    icon: Int,
    texto: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(text = texto)
    }
}


// Tarjeta individual de pedido
@Composable
fun TarjetaPedido(
    pedido: Pedido,
    onBotonDetallesPulsado: (Pedido) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoPedido(
                R.drawable.pizza,
                "${pedido.cantidadPizza} x Pizza ${pedido.pizza.nombre} (${pedido.pizza.tamaño})"
            )

            if (pedido.bebida.tipoBebida == tipoBebida.SIN_BEBIDA) {
                InfoPedido(R.drawable.cruz, "Sin bebida")
            } else {
                InfoPedido(
                    R.drawable.bebida,
                    "${pedido.cantidadBebida} x ${pedido.bebida.tipoBebida}"
                )
            }

            InfoPedido(R.drawable.dinero, "${pedido.precio} €")
            InfoPedido(R.drawable.calendario, "${pedido.fecha}")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { onBotonDetallesPulsado(pedido) },
                    colors = ButtonDefaults.buttonColors(RojoTomateLight),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(R.string.detalles))
                }
            }
        }
    }
}
