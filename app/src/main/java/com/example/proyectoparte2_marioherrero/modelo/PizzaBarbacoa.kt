package com.example.proyectoparte2_marioherrero.modelo

enum class TipoCarne {
    CERDO, POLLO, TERNERA
}

data class PizzaBarbacoa(
    val carne: TipoCarne,
    override val tamaño: TamañoPizza
) : Pizza(nombre = "Barbacoa", tamaño)

