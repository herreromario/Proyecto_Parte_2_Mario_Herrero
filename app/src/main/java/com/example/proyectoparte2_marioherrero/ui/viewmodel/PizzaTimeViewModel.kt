package com.example.proyectoparte2_marioherrero.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.proyectoparte2_marioherrero.datos.listaPedidos
import com.example.proyectoparte2_marioherrero.datos.listaUsuariosRegistrados
import com.example.proyectoparte2_marioherrero.modelo.Pedido
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaTimeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PizzaTimeUIState())
    val uiState: StateFlow<PizzaTimeUIState> = _uiState.asStateFlow()

    private lateinit var listaPedidosActual: List<Pedido>

    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarPedidosUsuario(idUsuario: Int){
        listaPedidosActual = listaPedidos.filter { it.idUsuario == idUsuario }

        _uiState.value = PizzaTimeUIState(
            listaPedidos = listaPedidosActual
        )
//        _uiState.update { estadoActual ->
//            estadoActual.copy(
//                listaPedidos = listaPedidosActual
//            )
//        }
    }
}
