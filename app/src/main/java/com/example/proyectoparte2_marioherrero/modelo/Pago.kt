package com.example.proyectoparte2_marioherrero.modelo

enum class tipoTarjeta {
    VISA, MasterCard, Euro6000
}

data class Pago(
    val id: Int,
    val tipoTarjeta: tipoTarjeta,
    val numeroTarjeta: String,
    val fechaCaducidad: String,
    val cvc: String
)