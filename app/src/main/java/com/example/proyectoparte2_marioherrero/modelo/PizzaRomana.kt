package com.example.proyectoparte2_marioherrero.modelo

data class PizzaRomana(
    val champiñones: Boolean
) : Pizza(nombre = "Romana", TamañoPizza.PEQUEÑA)