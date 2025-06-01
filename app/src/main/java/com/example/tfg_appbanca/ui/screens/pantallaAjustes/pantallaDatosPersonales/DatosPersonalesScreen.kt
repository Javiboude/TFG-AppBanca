package com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaDatosPersonales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.ui.screens.pantallaTarjetas.infoTarjeta.InfoItem
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun DatosPersonalesScreen(
    navController: NavController,
    datosPersonalesViewModel: DatosPersonalesViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val infoTarjeta by datosPersonalesViewModel.infoTarjeta.collectAsStateWithLifecycle()
    val usuario by datosPersonalesViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        datosPersonalesViewModel.getUsuarioInfo(numeroTelefono)
        datosPersonalesViewModel.cargarInfoTarjeta(numeroTelefono)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        infoTarjeta?.let {
            if (usuario != null && infoTarjeta != null) {
                InfoTarjeta(
                    titular = usuario!!.nombre,
                    password = usuario!!.password,
                    numeroTelefono = numeroTelefono,
                    numeroTarjeta = infoTarjeta!!.numeroTarjeta,
                    cuentaAsociada = infoTarjeta!!.cuentaAsociada,
                    enviar = {
                        navController.navigate("${Destinations.PANTALLA_AJUSTES}")
                    }
                )
            }
        }
    }
}


@Composable
fun InfoTarjeta(
    titular: String,
    password: String?,
    numeroTelefono: String,
    numeroTarjeta: String,
    cuentaAsociada: String,
    enviar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        InfoItem(titulo = "Titular", valor = titular)
        InfoItem(titulo = "Numero Telefono", valor = numeroTelefono)
        InfoItem(titulo = "Contraseña", valor = password ?: "No disponible")
        InfoItem(titulo = "Número tarjeta", valor = numeroTarjeta)
        InfoItem(titulo = "Cuenta asociada", valor = cuentaAsociada)
    }
}