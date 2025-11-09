package com.example.proyectoparte2_marioherrero.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.theme.AmarilloQuesoLight
import com.example.proyectoparte2_marioherrero.ui.theme.RojoTomateLight

@Composable
fun PantallaPedidoRealizado(
    onVolverInicio: () -> Unit,
    modifier: Modifier = Modifier,
    pizzaTimeUIState: PizzaTimeUIState
) {
    val pedido = pizzaTimeUIState.pedidoActual
    val usuario = pizzaTimeUIState.usuarioActual

    val context = LocalContext.current

    fun enviarJustificantePorCorreo() {
        val destinatario = usuario.correo // o el correo del usuario
        val asunto = "Justificante del pedido #${pedido?.id ?: "-"}"
        val mensaje = buildString {
            appendLine("¡Gracias por tu pedido!")
            appendLine("Aquí tienes el justificante de tu compra:")
            appendLine("")
            appendLine("Pedido #${pedido?.id ?: "-"}")
            appendLine("Pizza: ${pedido?.cantidadPizza} x ${pedido?.pizza?.nombre}")
            appendLine("Bebida: ${pedido?.cantidadBebida} x ${pedido?.bebida?.tipoBebida}")
            appendLine("Precio total: ${pedido?.precio} €")
            appendLine("")
            appendLine("Método de pago: ${pedido?.pago?.tipoTarjeta}")
            appendLine("Tarjeta terminada en ${pedido?.pago?.numeroTarjeta?.takeLast(4)}")
            appendLine("")
            appendLine("¡Disfruta tu pizza!")
        }

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$destinatario")
            putExtra(Intent.EXTRA_SUBJECT, asunto)
            putExtra(Intent.EXTRA_TEXT, mensaje)
        }

        // Lanza la app de correo
        context.startActivity(intent)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "¡Enhorabuena!",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Tu pedido #${pedido?.id ?: "-"} ha sido confirmado",
            style = MaterialTheme.typography.titleLarge
        )

        TarjetaDetallesPedido(pedido = pedido)


        // Botón enviar justificante
        Button(
            onClick = { enviarJustificantePorCorreo() },
            colors = ButtonDefaults.buttonColors(containerColor = AmarilloQuesoLight),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 60.dp, width = 250.dp)
        ) {
            Text(
                text = "Enviar justificante por correo",
                style = MaterialTheme.typography.titleMedium
            )
        }


        Button(
            onClick = onVolverInicio,
            colors = ButtonDefaults.buttonColors(containerColor = AmarilloQuesoLight),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 60.dp, width = 250.dp)
        ) {
            Text(
                text = "Volver al inicio",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
