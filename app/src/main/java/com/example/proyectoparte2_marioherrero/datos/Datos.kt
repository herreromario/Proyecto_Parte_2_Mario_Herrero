package com.example.proyectoparte2_marioherrero.datos

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Bebida
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.PizzaBarbacoa
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.PizzaRomana
import com.example.proyectoparte2_marioherrero.modelo.TamañoPizza
import com.example.proyectoparte2_marioherrero.modelo.TipoCarne
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
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
val listaPedidos: List<Pedido> = listOf(
    // Usuario 1
    Pedido(
        id = 1,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 8, 5),
        pizza = PizzaMargarita(piña = false, vegana = false, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 1,
        precio = 0.0
    ),
    Pedido(
        id = 2,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 8, 28),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 2,
        precio = 0.0
    ),
    Pedido(
        id = 3,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 9, 14),
        pizza = PizzaBarbacoa(carne = TipoCarne.POLLO, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.SIN_BEBIDA),
        cantidadBebida = 0,
        precio = 0.0
    ),
    Pedido(
        id = 4,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 10, 6),
        pizza = PizzaMargarita(piña = true, vegana = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 0.0
    ),
    Pedido(
        id = 5,
        idUsuario = 1,
        fecha = LocalDate.of(2025, 11, 2),
        pizza = PizzaRomana(champiñones = false, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 0.0
    ),

    // Usuario 2
    Pedido(
        id = 6,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 8, 10),
        pizza = PizzaBarbacoa(carne = TipoCarne.CERDO, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 1,
        precio = 0.0
    ),
    Pedido(
        id = 7,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 9, 2),
        pizza = PizzaMargarita(piña = true, vegana = false, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 2,
        precio = 0.0
    ),
    Pedido(
        id = 8,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 9, 21),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.SIN_BEBIDA),
        cantidadBebida = 0,
        precio = 0.0
    ),
    Pedido(
        id = 9,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 10, 17),
        pizza = PizzaBarbacoa(carne = TipoCarne.TERNERA, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 3,
        precio = 0.0
    ),
    Pedido(
        id = 10,
        idUsuario = 2,
        fecha = LocalDate.of(2025, 11, 1),
        pizza = PizzaMargarita(piña = false, vegana = true, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 0.0
    ),

    // Usuario 3
    Pedido(
        id = 11,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 8, 15),
        pizza = PizzaRomana(champiñones = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 0.0
    ),
    Pedido(
        id = 12,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 9, 6),
        pizza = PizzaBarbacoa(carne = TipoCarne.TERNERA, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 0.0
    ),
    Pedido(
        id = 13,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 9, 26),
        pizza = PizzaMargarita(piña = true, vegana = true, tamaño = TamañoPizza.GRANDE),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 1,
        precio = 0.0
    ),
    Pedido(
        id = 14,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 10, 12),
        pizza = PizzaRomana(champiñones = false, tamaño = TamañoPizza.PEQUEÑA),
        cantidadPizza = 2,
        bebida = Bebida(tipoBebida.COLA),
        cantidadBebida = 2,
        precio = 0.0
    ),
    Pedido(
        id = 15,
        idUsuario = 3,
        fecha = LocalDate.of(2025, 10, 29),
        pizza = PizzaBarbacoa(carne = TipoCarne.POLLO, tamaño = TamañoPizza.MEDIANA),
        cantidadPizza = 3,
        bebida = Bebida(tipoBebida.AGUA),
        cantidadBebida = 3,
        precio = 0.0
    )
)


// Precios de los productos

val precioPizzaPequeña: Double = 4.95
val precioPizzaMediana: Double = 6.95
val precioPizzaGrande: Double = 10.95

val precioAgua: Double = 2.0
val precioCola: Double = 2.5
