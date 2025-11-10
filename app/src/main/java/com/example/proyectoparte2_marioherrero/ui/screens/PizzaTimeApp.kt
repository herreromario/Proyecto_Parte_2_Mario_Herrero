package com.example.proyectoparte2_marioherrero.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.proyectoparte2_marioherrero.R
import com.example.proyectoparte2_marioherrero.modelo.Bebida
import com.example.proyectoparte2_marioherrero.modelo.tipoBebida
import com.example.proyectoparte2_marioherrero.modelo.uistate.PizzaTimeUIState
import com.example.proyectoparte2_marioherrero.ui.viewmodel.PizzaTimeViewModel

enum class Pantallas(@StringRes val titulo: Int) {
    PantallaInicial(titulo = R.string.pantalla_inicial),
    PantallaListarPedidos(titulo = R.string.pantalla_listar_pedidos),
    PantallaDetallesPedido(titulo = R.string.pantalla_detalles_pedido),
    PantallaRealizarPedido(titulo = R.string.pantalla_realizar_pedido),
    PantallaFormularioPago(titulo = R.string.pantalla_formulario_pago),
    PantallaResumenPedido(titulo = R.string.pantalla_resumen_de_pedido),
    PantallaPedidoRealizado(titulo = R.string.pantalla_pedido_realizado)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PizzaTimeApp(
    pizzaTimeViewModel: PizzaTimeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.PantallaInicial.name
    )

    val pizzaTimeUIState by pizzaTimeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = {
                    if (pantallaActual == Pantallas.PantallaDetallesPedido) {
                        pizzaTimeViewModel.cargarPedidosUsuario(pizzaTimeUIState.usuarioActual.id)
                    }
                    navController.navigateUp()
                }

            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Pantallas.PantallaInicial.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Pantallas.PantallaInicial.name) {
                PantallaInicial(
                    onBotonListarPedidosPulsado = {
                        pizzaTimeViewModel.cargarPedidosUsuario(pizzaTimeUIState.usuarioActual.id)
                        navController.navigate(Pantallas.PantallaListarPedidos.name)
                    },
                    onBotonRealizarPedidoPulsado = {
                        pizzaTimeViewModel.iniciarPedido()
                        navController.navigate(Pantallas.PantallaRealizarPedido.name)
                    },
                    modifier = Modifier.fillMaxSize(),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }

            composable(route = Pantallas.PantallaListarPedidos.name) {
                PantallaListarPedidos(
                    onBotonDetallesPulsado = { pedido ->
                        pizzaTimeViewModel.seleccionarPedido(pedido)
                        navController.navigate(Pantallas.PantallaDetallesPedido.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState,
                )
            }

            composable(route = Pantallas.PantallaDetallesPedido.name) {
                PantallaDetallesPedido(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }

            composable(route = Pantallas.PantallaRealizarPedido.name) {
                PantallaRealizarPedido(
                    onPizzaSelecionada = { pizza ->
                        pizzaTimeViewModel.seleccionarPizza(pizza)
                    },
                    onPiñaPulsado = { pizzaTimeViewModel.onPiñaPulsado() },
                    onVeganoPulsado = { pizzaTimeViewModel.onVeganoPulsado()},
                    onChampiñonesPulsado = { pizzaTimeViewModel.onChampiñonesPulsado() },
                    onCarneSeleccionada = { carne ->
                        pizzaTimeViewModel.cambiarCarne(carne)
                    },
                    onTamañoSeleccionado = { tamaño ->
                        pizzaTimeViewModel.seleccionarTamaño(tamaño)
                                           },
                    onAumentarCantidadPizza = { pizzaTimeViewModel.aumentarCantidadPizza() },
                    onDisminuirCantidadPizza = { pizzaTimeViewModel.disminuirCantidadPizza() },
                    onBebidaSeleccionada = { bebida ->
                        pizzaTimeViewModel.seleccionarBebida(Bebida(bebida))
                    },
                    onAumentarCantidadBebida = { pizzaTimeViewModel.aumentarCantidadBebida() },
                    onDisminuirCantidadBebida = { pizzaTimeViewModel.disminuirCantidadBebida() },
                    onProcecerPago = {navController.navigate(Pantallas.PantallaFormularioPago.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }

            composable(route = Pantallas.PantallaFormularioPago.name){
                PantallaFormularioPago(
                    onTarjetaSeleccionada = { tarjeta ->
                        pizzaTimeViewModel.seleccionarTipoTarjeta(tarjeta)
                    },
                    onCambiarNumeroTarjeta = { pizzaTimeViewModel.cambiarNumeroTarjeta(it) },
                    onCambiarFechaCaducidad = { pizzaTimeViewModel.cambiarFechaCaducidad(it)},
                    onCambiarCodigoSeguridad = { pizzaTimeViewModel.cambiarCodigoSeguridad(it) },
                    onPagarPulsado = { navController.navigate(Pantallas.PantallaResumenPedido.name)
                        pizzaTimeViewModel.agregarPedido()},
                    onVolverAtrasPulsado = { navController.navigate(Pantallas.PantallaRealizarPedido.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }

            composable(route = Pantallas.PantallaResumenPedido.name){
                PantallaResumenPedido(
                    onFinalizarPedido = { navController.navigate(Pantallas.PantallaPedidoRealizado.name)},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }

            composable(route = Pantallas.PantallaPedidoRealizado.name){
                PantallaPedidoRealizado(
                    onVolverInicio = { navController.popBackStack(Pantallas.PantallaInicial.name, inclusive = false)
                                     pizzaTimeViewModel.limpiarPedidoActual()},
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    pizzaTimeUIState = pizzaTimeUIState
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.atras)
                    )
                }
            }
        }
    )
}