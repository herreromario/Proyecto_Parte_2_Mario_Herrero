package com.example.proyectoparte2_marioherrero.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectoparte2_marioherrero.datos.listaUsuariosRegistrados
import com.example.proyectoparte2_marioherrero.modelo.Usuario
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PizzaTimeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PizzaTimeUIState())
    val uiState: StateFlow<PizzaTimeUIState> = _uiState.asStateFlow()

    private var usuarioActual: Usuario = listaUsuariosRegistrados[0]

}