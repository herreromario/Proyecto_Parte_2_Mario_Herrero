package com.example.proyectoparte2_marioherrero.modelo

sealed class Pizza(
    open val nombre: String,
    open val tamaño: TamañoPizza
)

enum class TamañoPizza{
    PEQUEÑA, MEDIANA, GRANDE
}
