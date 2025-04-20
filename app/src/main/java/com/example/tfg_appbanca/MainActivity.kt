package com.example.tfg_appbanca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.httpclienttest.ui.navigation.NavGraph
import com.example.tfg_appbanca.ui.components.BottomNavigationBar
import com.example.tfg_appbanca.ui.components.TopBar2
import com.example.tfg_appbanca.ui.components.Topbar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val currentRoute = remember { mutableStateOf(Destinations.PANTALLA_DE_CARGA_URL) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route ?: Destinations.PANTALLA_DE_CARGA_URL
        }
    }

    Scaffold(
        topBar = {
            if (currentRoute.value != Destinations.PANTALLA_DE_CARGA_URL &&
                currentRoute.value != Destinations.PANTALLA_LOGIN_URL &&
                currentRoute.value != Destinations.CREAR_CUENTA_1_URL &&
                currentRoute.value != Destinations.CREAR_CUENTA_2_URL &&
                currentRoute.value != Destinations.PANTALLA_BIZUM_URL &&
                currentRoute.value != Destinations.PANTALLA_TRANSFERENCIA_URL
            ) {
                Topbar(currentRoute)
            } else if (
                currentRoute.value == Destinations.PANTALLA_BIZUM_URL ||
                currentRoute.value == Destinations.PANTALLA_TRANSFERENCIA_URL
                ) {
                TopBar2(
                    currentRoute = currentRoute.value,  // Pasamos el valor actual
                    onHomeClick = {
                        navController.navigate(Destinations.PANTALLA_INICIO_URL) {
                            popUpTo(Destinations.PANTALLA_INICIO_URL) { inclusive = true }
                        }
                    }
                )
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavGraph(navController)
            }
        },
        bottomBar = {
            // Mostrar BottomNavigationBar solo si NO estamos en estas rutas
            if (currentRoute.value != Destinations.PANTALLA_DE_CARGA_URL &&
                currentRoute.value != Destinations.PANTALLA_LOGIN_URL &&
                currentRoute.value != Destinations.CREAR_CUENTA_1_URL &&
                currentRoute.value != Destinations.CREAR_CUENTA_2_URL &&
                currentRoute.value != Destinations.PANTALLA_BIZUM_URL &&
                currentRoute.value != Destinations.PANTALLA_TRANSFERENCIA_URL
            ) {
                BottomNavigationBar(
                    currentRoute = currentRoute.value,
                    onHomeClick = {
                        navController.navigate(Destinations.PANTALLA_INICIO_URL) {
                            popUpTo(Destinations.PANTALLA_INICIO_URL) { inclusive = true }
                        }
                    },
                    onCardsClick = {
                        navController.navigate(Destinations.PANTALLA_TARJETAS_URL)
                    },
                    onSavingsClick = {
                        navController.navigate(Destinations.PANTALLA_AHORROS_URL)
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}