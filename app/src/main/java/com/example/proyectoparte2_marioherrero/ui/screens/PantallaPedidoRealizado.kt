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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.R
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
        val asunto = context.getString(R.string.justificante_del_pedido, pedido?.id ?: "-")
        val mensaje = buildString {
            appendLine(context.getString(R.string.gracias_por_tu_pedido))
            appendLine(context.getString(R.string.aqu_tienes_el_justificante_de_tu_compra))
            appendLine("")
            appendLine(context.getString(R.string.pedido_id, pedido?.id ?: "-"))
            appendLine(context.getString(R.string.pizza_x, pedido?.cantidadPizza, pedido?.pizza?.nombre))
            appendLine(context.getString(R.string.bebida_x, pedido?.cantidadBebida, pedido?.bebida?.tipoBebida))
            appendLine(context.getString(R.string.precio_total, pedido?.precio))
            appendLine("")
            appendLine(context.getString(R.string.m_todo_de_pago, pedido?.pago?.tipoTarjeta))
            appendLine(context.getString(R.string.tarjeta_terminada_en, pedido?.pago?.numeroTarjeta?.takeLast(4)))
            appendLine("")
            appendLine(context.getString(R.string.disfruta_tu_pizza))
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
            text = stringResource(R.string.enhorabuena),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(R.string.tu_pedido_ha_sido_confirmado, pedido?.id ?: "-"),
            style = MaterialTheme.typography.titleLarge
        )

        TarjetaDetallesPedido(pedido = pedido)

        // Botón enviar justificante
        Button(
            onClick = { enviarJustificantePorCorreo() },
            colors = ButtonDefaults.buttonColors(containerColor = RojoTomateLight),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(20.dp)
                .size(width = 300.dp, height = 70.dp)
        ) {
            Text(
                text = stringResource(R.string.enviar_justificante_por_correo),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Button(
            onClick = onVolverInicio,
            colors = ButtonDefaults.buttonColors(containerColor = RojoTomateLight),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(20.dp)
                .size(width = 250.dp, height = 70.dp)
        ) {
            Text(
                text = stringResource(R.string.volver_al_inicio),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
