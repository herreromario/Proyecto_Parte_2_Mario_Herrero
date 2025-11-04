package com.example.proyectoparte2_marioherrero.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.ui.viewmodel.PizzaTimeViewModel

@Composable
fun PantallaInicial(
    modifier: Modifier = Modifier,
    pizzaTimeViewModel: PizzaTimeViewModel = viewModel()
) {
    val pizzaTimeUIState by pizzaTimeViewModel.uiState.collectAsState()

    val usuario = pizzaTimeUIState.usuarioActual

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TarjetaUsuario(usuario = usuario)

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun TarjetaUsuario(usuario: Usuario, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(usuario.fotoPerfil),
                contentDescription = usuario.id.toString(),
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.displaySmall
            )
            Text(text = usuario.apellido, style = MaterialTheme.typography.bodyLarge)
            Text(text = usuario.correo, style = MaterialTheme.typography.bodyLarge)
            Text(text = usuario.telefono, style = MaterialTheme.typography.bodyLarge)
        }
    }
}