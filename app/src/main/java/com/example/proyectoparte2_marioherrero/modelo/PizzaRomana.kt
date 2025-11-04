package com.example.proyectoparte2_marioherrero.modelo

data class PizzaRomana(
    val champiñones: Boolean,
    override val tamaño: TamañoPizza
) : Pizza(nombre = "Romana", tamaño)