package com.example.proyectoparte2_marioherrero.datos

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Bebida
import com.example.proyectoparte2_marioherrero.modelo.Pago
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Pizza
import com.example.proyectoparte2_marioherrero.modelo.PizzaBarbacoa
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.PizzaRomana
import com.example.proyectoparte2_marioherrero.modelo.TamañoPizza
import com.example.proyectoparte2_marioherrero.modelo.TipoCarne
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
import com.example.proyectoparte2_marioherrero.modelo.tipoTarjeta
import java.time.LocalDate

val listaUsuariosRegistrados: List<Usuario> = listOf(
    Usuario(
        id = 1,
        nombre = "Mario",
        apellido = "Herrero",
        correo = "always.haxe@gmail.com",
        telefono = "34611480475",
        fotoPerfil = R.drawable.fotoperfil1
    ),
    Usuario(
        id = 2,
        nombre = "Carlos",
        apellido = "Cacho",
        correo = "c.cacholopez@edu.gva.es",
        telefono = "34612345666",
        fotoPerfil = R.drawable.fotocarlos
    ),
    Usuario(
        id = 3,
        nombre = "Luis",
        apellido = "Torres",
        correo = "luistorres@hotmail.com",
        telefono = "34621345678",
        fotoPerfil = R.drawable.fotovacia
    ),
)

@RequiresApi(Build.VERSION_CODES.O)
var listaPedidos= mutableListOf(
    // Usuario 1 - Usa dos tarjetas (VISA y MasterCard)
    Pedido(
        id = 1,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 8, 5),
        pizza = PizzaMargarita(piña = false, vegana = false, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 1,
        precio = 9.45,
        pago = Pago(
            id = 1,
            tipoTarjeta = tipoTarjeta.VISA,
            numeroTarjeta = "4111 2222 3333 4444",
            fechaCaducidad = "08/27",
            cvc = "123"
        )
    ),
    Pedido(
        id = 2,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 8, 28),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 2,
        precio = 25.90,
        pago = Pago(
            id = 2,
            tipoTarjeta = tipoTarjeta.VISA,
            numeroTarjeta = "4111 2222 3333 4444",
            fechaCaducidad = "08/27",
            cvc = "123"
        )
    ),
    Pedido(
        id = 3,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 9, 14),
        pizza = PizzaBarbacoa(carne = TipoCarne.POLLO, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.SIN_BEBIDA),
        cantidadBebida = 0,
        precio = 14.85,
        pago = Pago(
            id = 3,
            tipoTarjeta = tipoTarjeta.MasterCard,
            numeroTarjeta = "5500 6666 7777 8888",
            fechaCaducidad = "12/28",
            cvc = "456"
        )
    ),
    Pedido(
        id = 4,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 10, 6),
        pizza = PizzaMargarita(piña = true, vegana = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 15.95,
        pago = Pago(
            id = 4,
            tipoTarjeta = tipoTarjeta.MasterCard,
            numeroTarjeta = "5500 6666 7777 8888",
            fechaCaducidad = "12/28",
            cvc = "456"
        )
    ),
    Pedido(
        id = 5,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 11, 2),
        pizza = PizzaRomana(champiñones = false, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 11.90,
        pago = Pago(
            id = 5,
            tipoTarjeta = tipoTarjeta.VISA,
            numeroTarjeta = "4111 2222 3333 4444",
            fechaCaducidad = "08/27",
            cvc = "123"
        )
    ),

    // Usuario 2 - Usa solo una tarjeta (Euro6000)
    Pedido(
        id = 6,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 8, 10),
        pizza = PizzaBarbacoa(carne = TipoCarne.CERDO, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 1,
        precio = 9.45,
        pago = Pago(
            id = 6,
            tipoTarjeta = tipoTarjeta.Euro6000,
            numeroTarjeta = "6011 1234 5678 9999",
            fechaCaducidad = "07/26",
            cvc = "321"
        )
    ),
    Pedido(
        id = 7,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 9, 2),
        pizza = PizzaMargarita(piña = true, vegana = false, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 2,
        precio = 25.90,
        pago = Pago(
            id = 7,
            tipoTarjeta = tipoTarjeta.Euro6000,
            numeroTarjeta = "6011 1234 5678 9999",
            fechaCaducidad = "07/26",
            cvc = "321"
        )
    ),
    Pedido(
        id = 8,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 9, 21),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.SIN_BEBIDA),
        cantidadBebida = 0,
        precio = 6.95,
        pago = Pago(
            id = 8,
            tipoTarjeta = tipoTarjeta.Euro6000,
            numeroTarjeta = "6011 1234 5678 9999",
            fechaCaducidad = "07/26",
            cvc = "321"
        )
    ),
    Pedido(
        id = 9,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 10, 17),
        pizza = PizzaBarbacoa(carne = TipoCarne.TERNERA, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 3,
        precio = 40.35,
        pago = Pago(
            id = 9,
            tipoTarjeta = tipoTarjeta.Euro6000,
            numeroTarjeta = "6011 1234 5678 9999",
            fechaCaducidad = "07/26",
            cvc = "321"
        )
    ),
    Pedido(
        id = 10,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 11, 1),
        pizza = PizzaMargarita(piña = false, vegana = true, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 6.95,
        pago = Pago(
            id = 10,
            tipoTarjeta = tipoTarjeta.Euro6000,
            numeroTarjeta = "6011 1234 5678 9999",
            fechaCaducidad = "07/26",
            cvc = "321"
        )
    ),

    // Usuario 3 - Usa dos tarjetas (MasterCard y VISA)
    Pedido(
        id = 11,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 8, 15),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 12.95,
        pago = Pago(
            id = 11,
            tipoTarjeta = tipoTarjeta.MasterCard,
            numeroTarjeta = "5500 9999 1111 2222",
            fechaCaducidad = "03/27",
            cvc = "987"
        )
    ),
    Pedido(
        id = 12,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 9, 6),
        pizza = PizzaBarbacoa(carne = TipoCarne.TERNERA, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 18.90,
        pago = Pago(
            id = 12,
            tipoTarjeta = tipoTarjeta.MasterCard,
            numeroTarjeta = "5500 9999 1111 2222",
            fechaCaducidad = "03/27",
            cvc = "987"
        )
    ),
    Pedido(
        id = 13,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 9, 26),
        pizza = PizzaMargarita(piña = true, vegana = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 12.95,
        pago = Pago(
            id = 13,
            tipoTarjeta = tipoTarjeta.VISA,
            numeroTarjeta = "4000 8888 7777 6666",
            fechaCaducidad = "09/28",
            cvc = "654"
        )
    ),
    Pedido(
        id = 14,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 10, 12),
        pizza = PizzaRomana(champiñones = false, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 14.90,
        pago = Pago(
            id = 14,
            tipoTarjeta = tipoTarjeta.MasterCard,
            numeroTarjeta = "5500 9999 1111 2222",
            fechaCaducidad = "03/27",
            cvc = "987"
        )
    ),
    Pedido(
        id = 15,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 10, 29),
        pizza = PizzaBarbacoa(carne = TipoCarne.POLLO, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 3,
        precio = 29.85,
        pago = Pago(
            id = 15,
            tipoTarjeta = tipoTarjeta.VISA,
            numeroTarjeta = "4000 8888 7777 6666",
            fechaCaducidad = "09/28",
            cvc = "654"
        )
    )
)

val listaPizzasDisponibles: List<Pizza> = listOf(
    PizzaMargarita(tamaño = TamañoPizza.PEQUEÑA, piña = false, vegana = false),
    PizzaBarbacoa(tamaño = TamañoPizza.PEQUEÑA, carne = TipoCarne.POLLO),
    PizzaRomana(tamaño = TamañoPizza.PEQUEÑA, champiñones = false)
)

// Precios de los productos

val precioPizzaPequeña: Double = 4.95
val precioPizzaMediana: Double = 6.95
val precioPizzaGrande: Double = 10.95

val precioAgua: Double = 2.0
val precioCola: Double = 2.5
