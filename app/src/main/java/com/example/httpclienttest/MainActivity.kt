package com.example.httpclienttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.httpclienttest.ui.navigation.NavGraph
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuenta2Screen
import com.example.httpclienttest.ui.screens.crearCuenta2.CrearCuenta2ViewModel
import com.example.httpclienttest.ui.theme.HttpClientTestTheme

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

    // Usa HttpClientTestTheme para aplicar el tema de la aplicación
    HttpClientTestTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavGraph(navController = navController)
        }
    }
}