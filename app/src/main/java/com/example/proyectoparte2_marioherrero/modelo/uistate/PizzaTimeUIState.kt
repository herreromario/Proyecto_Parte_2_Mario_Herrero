package com.example.proyectoparte2_marioherrero.modelo.uistate

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.proyectoparte2_marioherrero.datos.listaUsuariosRegistrados
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Usuario

data class PizzaTimeUIState (
    val usuarioActual: Usuario = listaUsuariosRegistrados[1],
    val listaPedidos: List<Pedido> = emptyList()
    )