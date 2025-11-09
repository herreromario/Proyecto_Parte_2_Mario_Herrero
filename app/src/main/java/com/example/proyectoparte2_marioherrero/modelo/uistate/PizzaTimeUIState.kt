package com.example.proyectoparte2_marioherrero.modelo.uistate

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.proyectoparte2_marioherrero.datos.listaUsuariosRegistrados
import com.example.proyectoparte2_marioherrero.modelo.Bebida
import com.example.proyectoparte2_marioherrero.modelo.Pago
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.PizzaMargarita
import com.example.proyectoparte2_marioherrero.modelo.TamañoPizza
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
import com.example.proyectoparte2_marioherrero.modelo.tipoTarjeta
import java.time.LocalDate

data class PizzaTimeUIState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val usuarioActual: Usuario = listaUsuariosRegistrados[0],
    val listaPedidos: List<Pedido> = emptyList(),
    val pedidoSeleccionado: Pedido? = null,
    val pedidoActual: Pedido? = Pedido(
        id = 0,
        idUsuario = usuarioActual.id,
        pizza = PizzaMargarita(tamaño = TamañoPizza.PEQUEÑA, piña = false, vegana = false),
        cantidadPizza = 1,
        bebida = Bebida(tipoBebida.SIN_BEBIDA),
        cantidadBebida = 0,
        fecha = LocalDate.now(),
        pago = Pago(0, tipoTarjeta.VISA, "", "", ""),
        precio = 0.0
    )
)