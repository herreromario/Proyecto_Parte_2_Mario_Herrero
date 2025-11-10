package com.example.proyectoparte2_marioherrero.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.proyectoparte2_marioherrero.datos.*
import com.example.proyectoparte2_marioherrero.modelo.*
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class PizzaTimeViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(PizzaTimeUIState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<PizzaTimeUIState> = _uiState.asStateFlow()

    private lateinit var listaPedidosActual: List<Pedido>


    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarPedidosUsuario(idUsuario: Int) {
        listaPedidosActual = listaPedidos.filter { it.idUsuario == idUsuario }
        listaPedidosActual = listaPedidosActual.reversed()
        _uiState.value = PizzaTimeUIState(listaPedidos = listaPedidosActual)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun iniciarPedido() {
        val pedidoBase = _uiState.value.pedidoActual ?: return


        val cantidadBebidaInicial = if (pedidoBase.bebida.tipoBebida == tipoBebida.SIN_BEBIDA) 0 else 1

        val pedidoInicial = pedidoBase.copy(
            cantidadBebida = cantidadBebidaInicial
        )

        val precioInicial = calcularPrecioPedido(pedidoInicial)

        _uiState.value = _uiState.value.copy(
            pedidoActual = pedidoInicial.copy(precio = precioInicial)
        )
    }

    // Seleccionar pedido ya existente
    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarPedido(pedido: Pedido) {
        _uiState.value = _uiState.value.copy(pedidoSeleccionado = pedido)
    }

    // Función general para recalcular precio y actualizar el pedido actual
    @RequiresApi(Build.VERSION_CODES.O)
    private fun actualizarPedido(pedidoActualizado: Pedido) {
        val nuevoPrecio = calcularPrecioPedido(pedidoActualizado)
        _uiState.update {
            it.copy(pedidoActual = pedidoActualizado.copy(precio = nuevoPrecio))
        }
    }

    // Cambiar tipo de pizza
    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarPizza(nuevaPizza: Pizza) {
        val pedido = _uiState.value.pedidoActual ?: return
        val tamañoActual = pedido.pizza.tamaño

        val pizzaConTamaño = when (nuevaPizza) {
            is PizzaMargarita -> nuevaPizza.copy(tamaño = tamañoActual)
            is PizzaBarbacoa -> nuevaPizza.copy(tamaño = tamañoActual)
            is PizzaRomana -> nuevaPizza.copy(tamaño = tamañoActual)
            else -> nuevaPizza
        }

        actualizarPedido(pedido.copy(pizza = pizzaConTamaño))
    }

    // Cambiar tamaño de pizza
    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarTamaño(tamaño: TamañoPizza) {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza

        val nuevaPizza = when (pizza) {
            is PizzaMargarita -> pizza.copy(tamaño = tamaño)
            is PizzaBarbacoa  -> pizza.copy(tamaño = tamaño)
            is PizzaRomana    -> pizza.copy(tamaño = tamaño)
            else              -> pizza
        }

        val pedidoActualizado = pedido.copy(pizza = nuevaPizza)
        val nuevoPrecio = calcularPrecioPedido(pedidoActualizado)

        _uiState.update { actual ->
            actual.copy(pedidoActual = pedidoActualizado.copy(precio = nuevoPrecio))
        }
    }


    // Cambiar carne (solo Barbacoa)
    @RequiresApi(Build.VERSION_CODES.O)
    fun cambiarCarne(carne: TipoCarne) {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza
        if (pizza is PizzaBarbacoa) {
            actualizarPedido(pedido.copy(pizza = pizza.copy(carne = carne)))
        }
    }

    // Cambiar propiedades extra
    @RequiresApi(Build.VERSION_CODES.O)
    fun onPiñaPulsado() {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza
        if (pizza is PizzaMargarita) {
            actualizarPedido(pedido.copy(pizza = pizza.copy(piña = !pizza.piña)))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onVeganoPulsado() {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza
        if (pizza is PizzaMargarita) {
            actualizarPedido(pedido.copy(pizza = pizza.copy(vegana = !pizza.vegana)))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onChampiñonesPulsado() {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza
        if (pizza is PizzaRomana) {
            actualizarPedido(pedido.copy(pizza = pizza.copy(champiñones = !pizza.champiñones)))
        }
    }

    // Aumentar / disminuir cantidad de pizzas
    @RequiresApi(Build.VERSION_CODES.O)
    fun aumentarCantidadPizza() {
        val pedido = _uiState.value.pedidoActual ?: return
        actualizarPedido(pedido.copy(cantidadPizza = pedido.cantidadPizza + 1))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun disminuirCantidadPizza() {
        val pedido = _uiState.value.pedidoActual ?: return
        if (pedido.cantidadPizza > 1) {
            actualizarPedido(pedido.copy(cantidadPizza = pedido.cantidadPizza - 1))
        }
    }

    // Cambiar bebida
    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarBebida(nuevaBebida: Bebida) {
        val pedido = _uiState.value.pedidoActual ?: return

        val cantidadInicial = if (nuevaBebida.tipoBebida == tipoBebida.SIN_BEBIDA) {
            0
        } else {
            1 // empieza siempre en 1 si no es SIN_BEBIDA
        }

        val pedidoActualizado = pedido.copy(
            bebida = nuevaBebida,
            cantidadBebida = cantidadInicial
        ).copy(precio = calcularPrecioPedido(pedido.copy(bebida = nuevaBebida, cantidadBebida = cantidadInicial)))

        _uiState.update {
            it.copy(pedidoActual = pedidoActualizado)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun aumentarCantidadBebida() {
        val pedido = _uiState.value.pedidoActual ?: return
        val pedidoActualizado = pedido.copy(
            cantidadBebida = pedido.cantidadBebida + 1
        ).copy(precio = calcularPrecioPedido(pedido.copy(cantidadBebida = pedido.cantidadBebida + 1)))

        _uiState.update {
            it.copy(pedidoActual = pedidoActualizado)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun disminuirCantidadBebida() {
        val pedido = _uiState.value.pedidoActual ?: return
        if (pedido.cantidadBebida > 1) { // nunca baja de 1
            val pedidoActualizado = pedido.copy(
                cantidadBebida = pedido.cantidadBebida - 1
            ).copy(precio = calcularPrecioPedido(pedido.copy(cantidadBebida = pedido.cantidadBebida - 1)))

            _uiState.update {
                it.copy(pedidoActual = pedidoActualizado)
            }
        }
    }


    // Calcular el precio total
    @RequiresApi(Build.VERSION_CODES.O)
    private fun calcularPrecioPedido(pedido: Pedido): Double {
        val precioPizza = when (pedido.pizza.tamaño) {
            TamañoPizza.PEQUEÑA -> precioPizzaPequeña
            TamañoPizza.MEDIANA -> precioPizzaMediana
            TamañoPizza.GRANDE -> precioPizzaGrande
        }

        val precioBebida = when (pedido.bebida.tipoBebida) {
            tipoBebida.AGUA -> precioAgua
            tipoBebida.COLA -> precioCola
            tipoBebida.SIN_BEBIDA -> 0.0
        }

        return (precioPizza * pedido.cantidadPizza) + (precioBebida * pedido.cantidadBebida)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarTipoTarjeta(tipoTarjeta: tipoTarjeta){
        val pedido = _uiState.value.pedidoActual ?: return
        val pago = pedido.pago

        actualizarPedido(pedido.copy(pago = pago.copy(tipoTarjeta = tipoTarjeta)))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun cambiarNumeroTarjeta(numeroTarjeta: String){
        val pedido = _uiState.value.pedidoActual ?: return
        val pago = pedido.pago

        actualizarPedido(pedido.copy(pago = pago.copy(numeroTarjeta = numeroTarjeta)))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun cambiarFechaCaducidad(fechaCaducidad: String){
        val pedido = _uiState.value.pedidoActual ?: return
        val pago = pedido.pago

        actualizarPedido(pedido.copy(pago = pago.copy(fechaCaducidad = fechaCaducidad)))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun cambiarCodigoSeguridad(codigoSeguridad: String){
        val pedido = _uiState.value.pedidoActual ?: return
        val pago = pedido.pago

        actualizarPedido(pedido.copy(pago = pago.copy(cvc = codigoSeguridad)))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarPedido() {
        val pedidoActual = _uiState.value.pedidoActual ?: return
        val usuario = _uiState.value.usuarioActual

        val nuevoId = listaPedidos.size + 1

        val pedidoFinal = pedidoActual.copy(
            id = nuevoId,
            idUsuario = usuario.id,
            pago = pedidoActual.pago.copy(id = nuevoId),
            fecha = LocalDate.now()
        )

        listaPedidos.add(pedidoFinal)

        _uiState.update {
            it.copy(
                listaPedidos = listaPedidos.filter { p -> p.idUsuario == usuario.id },
                pedidoSeleccionado = pedidoFinal,
                pedidoActual = pedidoFinal
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun limpiarPedidoActual() {
        val pedidoVacio = Pedido(
            id = 0,
            idUsuario = _uiState.value.usuarioActual.id,
            fecha = LocalDate.now(),
            pizza = PizzaMargarita(tamaño = TamañoPizza.PEQUEÑA, piña = false, vegana = false), // o Pizza() según tu modelo
            cantidadPizza = 0,
            bebida = Bebida(tipoBebida.SIN_BEBIDA),
            cantidadBebida = 0,
            precio = 0.0,
            pago = Pago(
                id = 0,
                tipoTarjeta = tipoTarjeta.VISA,
                numeroTarjeta = "",
                fechaCaducidad = "",
                cvc = ""
            )
        )

        _uiState.value = _uiState.value.copy(
            pedidoActual = pedidoVacio
        )
    }
}


