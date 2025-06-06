package com.example.httpclienttest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1Screen
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1ViewModel
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuenta2Screen
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuenta2ViewModel
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaScreen
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaViewModel
import com.example.httpclienttest.ui.screens.pantallaInicio.PantallaInicioScreen
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginScreen
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginViewModel
import com.example.tfg_appbanca.ui.screens.pantallaAhorros.PantallaAhorros
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum.PantallaBizumScreen
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum.PantallaBizumViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.PantallaInicioViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia.PantallaTransferenciaScreen
import com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia.PantallaTransferenciaViewModel
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.PantallaTarjetas


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
            val pantallaLoginViewModel: PantallaLoginViewModel = viewModel()
            PantallaLoginScreen(
                navController = navController,
                pantallaLoginViewModel = pantallaLoginViewModel
            )
        }

        composable(Destinations.CREAR_CUENTA_1_URL) {
            val crearCuenta1ViewModel: CrearCuenta1ViewModel = viewModel()
            CrearCuenta1Screen(
                navController = navController,
                crearCeunta1ViewModel = crearCuenta1ViewModel
            )
        }

        composable(Destinations.CREAR_CUENTA_2_URL) {
            val crearCuenta2ViewModel: CrearCuenta2ViewModel = viewModel()
            CrearCuenta2Screen(
                navController = navController,
                crearCuenta2ViewModel = crearCuenta2ViewModel
            )
        }

        composable(Destinations.PANTALLA_INICIO_URL) {
            val pantallaInicioViewModel: PantallaInicioViewModel = viewModel()
            PantallaInicioScreen(
                navController = navController,
                pantallaInicioViewModel = pantallaInicioViewModel
            )
        }

        composable(Destinations.PANTALLA_TARJETAS_URL) {
            PantallaTarjetas(
            )
        }

        composable(Destinations.PANTALLA_AHORROS_URL) {
            PantallaAhorros(
            )
        }

        composable(Destinations.PANTALLA_BIZUM_URL) {
            val pantallaBizumViewModel: PantallaBizumViewModel = viewModel()
            PantallaBizumScreen(
                navController = navController,
                pantallaBizumViewModel = pantallaBizumViewModel
            )
        }

        composable(Destinations.PANTALLA_TRANSFERENCIA_URL) {
            val pantallaTransferenciaViewModel: PantallaTransferenciaViewModel = viewModel()
            PantallaTransferenciaScreen(
                navController = navController,
                pantallaTransferenciaViewModel = pantallaTransferenciaViewModel
            )
        }
    }
}