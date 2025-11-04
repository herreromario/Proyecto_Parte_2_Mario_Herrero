package com.example.proyectoparte2_marioherrero.modelo.uistate

import com.example.proyectoparte2_marioherrero.datos.listaUsuariosRegistrados
import com.example.proyectoparte2_marioherrero.modelo.Usuario

class PizzaTimeUIState {
    val usuarioActual: Usuario = listaUsuariosRegistrados[0]
}