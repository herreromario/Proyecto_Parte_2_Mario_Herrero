package com.example.proyectoparte2_marioherrero.modelo

data class Bebida(
    val tipoBebida: tipoBebida
)

enum class tipoBebida{
    AGUA, COLA, SIN_BEBIDA
}

