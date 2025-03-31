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
import com.example.httpclienttest.ui.screens.pantallaInicio.PantallaDeInicio
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginScreen
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginViewModel


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
            PantallaDeInicio(
            )
        }
    }
}