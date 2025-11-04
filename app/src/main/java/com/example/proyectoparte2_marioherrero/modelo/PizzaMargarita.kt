package com.example.proyectoparte2_marioherrero.modelo

data class PizzaMargarita(
    val piña: Boolean,
    val vegana: Boolean,
    override val tamaño: TamañoPizza
) : Pizza(nombre = "Margarita", tamaño)