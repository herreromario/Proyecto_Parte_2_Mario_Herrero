package com.example.proyectoparte2_marioherrero.modelo

open class Pizza(
    open val nombre: String,
    open val tamaño: TamañoPizza
)

enum class TamañoPizza{
    PEQUEÑA, MEDIANA, GRANDE
}
