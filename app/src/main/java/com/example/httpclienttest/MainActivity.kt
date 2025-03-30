package com.example.httpclienttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1Screen
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1ViewModel
import com.example.httpclienttest.ui.screens.patallaLogin.PantallaLoginViewModel
import com.example.httpclienttest.ui.theme.HttpClientTestTheme

class MainActivity : ComponentActivity() {

    private val crearCuenta1ViewModel: CrearCuenta1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpClientTestTheme {
                Surface {
                    CrearCuenta1Screen(crearCuenta1ViewModel)
                }
            }
        }
    }
}