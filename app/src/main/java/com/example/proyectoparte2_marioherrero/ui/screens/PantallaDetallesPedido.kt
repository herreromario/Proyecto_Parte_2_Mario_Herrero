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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.PizzaBarbacoa
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.PizzaRomana
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.AmarilloQuesoLight

@Composable
fun PantallaDetallesPedido(
    modifier: Modifier = Modifier,
    pizzaTimeUIState: PizzaTimeUIState,
) {
    val pedido = pizzaTimeUIState.pedidoSeleccionado
    val usuario = pizzaTimeUIState.usuarioActual

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

        Spacer(modifier = Modifier.height(16.dp))

        TarjetaDetallesGenerales(pedido = pedido, usuario = usuario)
    }
}

@Composable
fun TarjetaDetallesGenerales(
    pedido: Pedido?,
    usuario: Usuario,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TarjetaDetallesUsuario(usuario)
        TarjetaDetallesPedido(pedido)
        TarjetaDetallesPago(pedido)
    }
}

@Composable
fun TarjetaDetallesUsuario(
    usuario: Usuario,
    modifier: Modifier = Modifier
) {
    SeccionTitulo("Datos de contacto")

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text("${usuario.nombre} ${usuario.apellido}")
            Text(usuario.correo)
            Text(usuario.telefono)
        }
    }
}

@Composable
fun TarjetaDetallesPedido(
    pedido: Pedido?,
    modifier: Modifier = Modifier
) {
    SeccionTitulo("Datos del pedido")

    // Tarjeta ID pedido y fecha
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (pedido == null) {
                CargandoDetalles()
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Pedido #${pedido.id}")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Realizado el día ${pedido.fecha}")
                }
            }
        }
    }

    // Tarjeta con detalles de productos
    Spacer(modifier = Modifier.height(16.dp))
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        if(pedido == null){
            CargandoDetalles()
        } else{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                InfoPedido(
                    R.drawable.pizza,
                    "${pedido?.cantidadPizza} x Pizza ${pedido?.pizza?.nombre} de tamaño ${pedido?.pizza?.tamaño}"
                )

                when (pedido?.pizza) {
                    is PizzaBarbacoa -> {
                        InfoPedido(R.drawable.carne, "Con carne de ${pedido.pizza.carne}")
                    }
                    is PizzaMargarita -> {
                        if (pedido.pizza.piña) {
                            InfoPedido(R.drawable.pi_a, "Con piña")
                        } else {
                            InfoPedido(R.drawable.cruz, "Sin piña")
                        }

                        if (pedido.pizza.vegana) {
                            InfoPedido(R.drawable.planta, "Opción vegana")
                        }
                    }
                    is PizzaRomana -> {
                        if (pedido.pizza.champiñones) {
                            InfoPedido(R.drawable.champi_on, "Con champiñones")
                        } else {
                            InfoPedido(R.drawable.cruz, "Sin champiñones")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (pedido?.bebida?.tipoBebida == tipoBebida.SIN_BEBIDA) {
                    InfoPedido(R.drawable.cruz, "Sin bebida")
                } else {
                    InfoPedido(R.drawable.bebida, "${pedido?.cantidadBebida} x ${pedido?.bebida?.tipoBebida}")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total: ${pedido?.precio} €",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Composable
fun TarjetaDetallesPago(
    pedido: Pedido?,
    modifier: Modifier = Modifier
) {
    SeccionTitulo("Método de pago")

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(AmarilloQuesoLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (pedido == null) {
                CargandoDetalles()
            } else{
                Text("Pagado con tarjeta ${pedido.pago.tipoTarjeta}")
                Text("Tarjeta acabada en ${pedido.pago.numeroTarjeta.takeLast(4)}")
            }
        }
    }
}

@Composable
fun SeccionTitulo(texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 4.dp, top = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = texto,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
@Composable
fun CargandoDetalles(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.cargando_detalles))
    }
}
