package com.example.proyectoparte2_marioherrero.modelo

import java.time.LocalDate

data class Pedido(
    val id: Int,
    val idUsuario: Int,
    val fecha: LocalDate,
    val pizza: Pizza,
    val cantidadPizza: Int,
    val bebida: Bebida,
    val cantidadBebida: Int,
    val precio: Double
)