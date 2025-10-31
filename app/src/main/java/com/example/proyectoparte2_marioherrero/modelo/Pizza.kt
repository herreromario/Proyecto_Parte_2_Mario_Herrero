package com.example.proyectoparte2_marioherrero.modelo

open class Pizza(
    open val nombre: String,
    open val tamaño: TamañoPizza
)
enum class TamañoPizza{
    PEQUEÑA, MEDIANA, GRANDE
}

data class PizzaRomana(
    val champiñones: Boolean
) : Pizza(nombre = "Romana", TamañoPizza.PEQUEÑA)

data class PizzaBarbacoa(
    val carne: TipoCarne
) : Pizza(nombre = "Barbacoa", TamañoPizza.PEQUEÑA)

enum class TipoCarne {
    CERDO, POLLO, TERNERA
}

data class PizzaMargarita(
    val piña: Boolean,
    val vegana: Boolean
) : Pizza(nombre = "Margarita", TamañoPizza.PEQUEÑA)