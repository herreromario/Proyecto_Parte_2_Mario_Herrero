package com.example.proyectoparte2_marioherrero.modelo

enum class TipoCarne {
    CERDO, POLLO, TERNERA
}

data class PizzaBarbacoa(
    val carne: TipoCarne
) : Pizza(nombre = "Barbacoa", TamañoPizza.PEQUEÑA)

