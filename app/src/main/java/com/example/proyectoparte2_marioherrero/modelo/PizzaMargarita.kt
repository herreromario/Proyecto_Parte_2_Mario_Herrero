package com.example.proyectoparte2_marioherrero.modelo

data class PizzaMargarita(
    val piña: Boolean,
    val vegana: Boolean
) : Pizza(nombre = "Margarita", TamañoPizza.PEQUEÑA)