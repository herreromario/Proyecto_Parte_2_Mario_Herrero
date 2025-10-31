package com.example.proyectoparte2_marioherrero.modelo

import android.media.Image

data class Usuario (
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String,
    val fotoPerfil: Int
)