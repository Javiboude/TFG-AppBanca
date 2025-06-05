package com.example.httpclienttest.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuentaScreen
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuentaViewModel
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaScreen
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaViewModel
import com.example.httpclienttest.ui.screens.pantallaInicio.PantallaInicioScreen
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginScreen
import com.example.tfg_appbanca.ui.screens.pantallaAhorros.PantallaAhorros
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.PantallaAjustesScreen
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.PantallaAjustesViewModel
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaAñadirDinero.AñadirDineroScreen
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaAñadirDinero.AñadirDineroViewModel
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaDatosPersonales.DatosPersonalesScreen
import com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaDatosPersonales.DatosPersonalesViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum.PantallaBizumScreen
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum.PantallaBizumViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.PantallaInicioViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia.PantallaTransferenciaScreen
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia.PantallaTransferenciaViewModel
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.PantallaTarjetasScreen
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.PantallaTarjetasViewModel
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.infoTarjeta.PantallaInfoTarjetaScreen
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.infoTarjeta.PantallaInfoTarjetaViewModel
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites.PantallaModificarLimites
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites.PantallaModificarLimitesViewModel
import com.example.tfg_appbanca.ui.screens.patallaLogin.PantallaLoginViewModel
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.PANTALLA_DE_CARGA_URL
    ) {
        composable(Destinations.PANTALLA_DE_CARGA_URL) {
            val pantallaDeCargaViewModel: PantallaDeCargaViewModel = viewModel()
            PantallaDeCargaScreen(
                navController = navController,
                pantallaDeCargaViewModel = pantallaDeCargaViewModel
            )
        }

        composable(Destinations.PANTALLA_LOGIN_URL) {
            val pantallaLoginViewModel: PantallaLoginViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))

            PantallaLoginScreen(
                navController = navController,
                pantallaLoginViewModel = pantallaLoginViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.CREAR_CUENTA_URL) {
            val crearCuenta2ViewModel: CrearCuentaViewModel = hiltViewModel()
            CrearCuentaScreen(
                navController = navController,
                crearCuenta2ViewModel = crearCuenta2ViewModel
            )
        }

        composable(Destinations.PANTALLA_INICIO_URL) {
            val pantallaInicioViewModel: PantallaInicioViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))

            PantallaInicioScreen(
                navController = navController,
                pantallaInicioViewModel = pantallaInicioViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_TARJETAS_URL) {
            val pantallaTarjetasViewModel: PantallaTarjetasViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaTarjetasScreen(
                navController = navController,
                pantallaTarjetasViewModel = pantallaTarjetasViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_AHORROS_URL) {
            PantallaAhorros(
            )
        }

        composable(Destinations.PANTALLA_BIZUM_URL) {
            val pantallaBizumViewModel: PantallaBizumViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaBizumScreen(
                navController = navController,
                viewModel = pantallaBizumViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_TRANSFERENCIA_URL) {
            val pantallaTransferenciaViewModel: PantallaTransferenciaViewModel  = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaTransferenciaScreen(
                navController = navController,
                pantallaTransferenciaViewModel = pantallaTransferenciaViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_INFO_TARJETA_URL) {
            val pantallaCancelarTarjetaViewModel: PantallaInfoTarjetaViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaInfoTarjetaScreen(
                navController = navController,
                pantallaCancelarTarjetaViewModel = pantallaCancelarTarjetaViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_MODIFICAR_LIMITES_URL) {
            val pantallaModificarLimitesViewModel: PantallaModificarLimitesViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaModificarLimites(
                navController = navController,
                pantallaModificarLimitesViewModel = pantallaModificarLimitesViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_AJUSTES) {
            val pantallaAjustesViewModel: PantallaAjustesViewModel  = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            PantallaAjustesScreen(
                navController = navController,
                pantallaAjustesViewModel = pantallaAjustesViewModel,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_AÑADIR_DINERO) {
            val añadirDineroViewModel: AñadirDineroViewModel  = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            AñadirDineroScreen(
                añadirDineroViewModel = añadirDineroViewModel,
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Destinations.PANTALLA_DATOS_PERSONALES) {
            val datosPersonalesViewModel: DatosPersonalesViewModel = hiltViewModel()
            val sharedViewModel: SharedViewModel = hiltViewModel(navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL))
            DatosPersonalesScreen(
                datosPersonalesViewModel = datosPersonalesViewModel,
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}