package com.example.httpclienttest.ui.screens.pantallaDeCarga

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R

@Composable
fun PantallaDeCargaScreen(
    navController: NavController,
    pantallaDeCargaViewModel: PantallaDeCargaViewModel
) {
    val isLoading by pantallaDeCargaViewModel.isLoading.collectAsState()

    // Define los colores del degradado
    val colorLeft = Color(0xFF157AF3) // Azul oscuro
    val colorRight = Color(0xFF0b1e61) // Azul claro

    // Crea un Brush horizontal con el degradado
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(colorLeft, colorRight)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoblanco), // Carga la imagen desde recursos
                contentDescription = "Logo de la aplicación", // Descripción para accesibilidad
                modifier = Modifier
                    .size(220.dp)
                    .padding(bottom = 20.dp)
            )

            Text(text = "Safe Money",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            Spacer(modifier = Modifier.height(150.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(bottom = 150.dp),
                    color = Color.White,
                    strokeWidth = 4.dp
                ) // Indicador de carga
            } else {
                LaunchedEffect(Unit) {
                    navController.navigate("${Destinations.PANTALLA_LOGIN_URL}")
                }
            }
        }
    }
}