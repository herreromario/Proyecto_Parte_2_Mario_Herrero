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

class PizzaTimeViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(PizzaTimeUIState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<PizzaTimeUIState> = _uiState.asStateFlow()

    private lateinit var listaPedidosActual: List<Pedido>

    // 🔹 Cargar pedidos de un usuario
    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarPedidosUsuario(idUsuario: Int) {
        listaPedidosActual = listaPedidos.filter { it.idUsuario == idUsuario }
        _uiState.value = PizzaTimeUIState(listaPedidos = listaPedidosActual)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun iniciarPedido() {
        val pedidoBase = _uiState.value.pedidoActual ?: return

        // Si la bebida no es SIN_BEBIDA, aseguramos cantidad mínima 1
        val cantidadBebidaInicial = if (pedidoBase.bebida.tipoBebida == tipoBebida.SIN_BEBIDA) 0 else 1

        val pedidoInicial = pedidoBase.copy(
            cantidadBebida = cantidadBebidaInicial
        )

        // Calculamos el precio inicial
        val precioInicial = calcularPrecioPedido(pedidoInicial)

        _uiState.value = _uiState.value.copy(
            pedidoActual = pedidoInicial.copy(precio = precioInicial)
        )
    }

    // 🔹 Seleccionar pedido ya existente
    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarPedido(pedido: Pedido) {
        _uiState.value = _uiState.value.copy(pedidoSeleccionado = pedido)
    }

    // 🔹 Función general para recalcular precio y actualizar el pedido actual
    @RequiresApi(Build.VERSION_CODES.O)
    private fun actualizarPedido(pedidoActualizado: Pedido) {
        val nuevoPrecio = calcularPrecioPedido(pedidoActualizado)
        _uiState.update {
            it.copy(pedidoActual = pedidoActualizado.copy(precio = nuevoPrecio))
        }
    }

    // 🔹 Cambiar tipo de pizza
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

    // 🔹 Cambiar tamaño de pizza
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


    // 🔹 Cambiar carne (solo Barbacoa)
    @RequiresApi(Build.VERSION_CODES.O)
    fun cambiarCarne(carne: TipoCarne) {
        val pedido = _uiState.value.pedidoActual ?: return
        val pizza = pedido.pizza
        if (pizza is PizzaBarbacoa) {
            actualizarPedido(pedido.copy(pizza = pizza.copy(carne = carne)))
        }
    }

    // 🔹 Cambiar propiedades extra
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

    // 🔹 Aumentar / disminuir cantidad de pizzas
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

    // 🔹 Cambiar bebida
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


    // 🔹 Calcular el precio total
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

    // 🔹 Actualizar pago
    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarPago(pago: Pago) {
        _uiState.update { actual ->
            actual.copy(pedidoActual = actual.pedidoActual?.copy(pago = pago))
        }
    }
}
