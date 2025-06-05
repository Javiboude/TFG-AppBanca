package com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaAñadirDinero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun AñadirDineroScreen(
    navController: NavController,
    añadirDineroViewModel: AñadirDineroViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val cantidadDinero = añadirDineroViewModel.cantidadDinero

    val numeroTelefonoUsuario by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()

    val operacionExitosa = añadirDineroViewModel.operacionExitosa
    val operacionFallida = añadirDineroViewModel.operacionFallida

    val camposValidos = cantidadDinero.isNotBlank()


    LaunchedEffect(Unit) {
        añadirDineroViewModel.getUsuarioInfo(numeroTelefonoUsuario)
    }

    if (operacionExitosa.value) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    añadirDineroViewModel.setOperacionExitosa(false)
                    navController.navigate(Destinations.PANTALLA_INICIO_URL)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Dinero añadido") },
            text = { Text("Se ha enviado el dinero correctamente.") }
        )
    }

    if (operacionFallida.value) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    añadirDineroViewModel.setOperacionExitosa(false)
                    navController.navigate(Destinations.PANTALLA_INICIO_URL)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("No se ha podido añadir") },
            text = { Text("No ha añadido el dinero.") }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = cantidadDinero,
            onValueChange = { añadirDineroViewModel.onCantidadDineroChange(it) },
            label = { Text("Cantidad a añadir") },
            modifier = Modifier.fillMaxWidth(),
            prefix = { Text("€") }
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                añadirDineroViewModel.añadirDinero(
                    telefono = numeroTelefonoUsuario
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A1D57),
                disabledContainerColor = Color(0xFFCCCCCC)
            ),
            enabled = camposValidos,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Añadir dinero")
        }
    }
}