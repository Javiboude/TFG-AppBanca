package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun PantallaModificarLimites(
    navController: NavController,
    pantallaModificarLimitesViewModel: PantallaModificarLimitesViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val limiteCajeros = pantallaModificarLimitesViewModel.limiteCajeros
    val limiteComercio = pantallaModificarLimitesViewModel.limiteComercio
    val usuario by pantallaModificarLimitesViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()
    val infoTarjeta by pantallaModificarLimitesViewModel.infoTarjeta.collectAsStateWithLifecycle()

    val cambiarLimitesExitoso by pantallaModificarLimitesViewModel.cambiarLimitesExitoso
    val cambiarLimitesFallido by pantallaModificarLimitesViewModel.cambiarLimitesFallido

    if (cambiarLimitesExitoso) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    pantallaModificarLimitesViewModel.setLimitesExitoso(false)
                    navController.navigate(Destinations.PANTALLA_TARJETAS_URL)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Cambiar Limites Exitoso") },
            text = { Text("Se han cambiado los límites.") }
        )
    }
    if (cambiarLimitesFallido) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    pantallaModificarLimitesViewModel.setLimitesFallido(false)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Cambiar Limites Fallido") },
            text = { Text("No se han cambiado los límites.") }
        )
    }

    LaunchedEffect(Unit) {
        pantallaModificarLimitesViewModel.getUsuarioInfo(numeroTelefono)
        pantallaModificarLimitesViewModel.cargarInfoTarjeta(numeroTelefono)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (usuario != null && infoTarjeta != null) {
        TarjetaInfo(
            saldo = usuario!!.dinero,
            limiteOnline = infoTarjeta!!.limiteOnline,
            limiteFisico = infoTarjeta!!.limiteFisico
        )
        }

        ModificarLimites(
            limiteCajeros = limiteCajeros,
            limiteCajerosCambio = { pantallaModificarLimitesViewModel.limiteCajeros = it },
            limiteComercio = limiteComercio,
            limiteComercioCambios = { pantallaModificarLimitesViewModel.limiteComercio = it },
            enviar = {
                pantallaModificarLimitesViewModel.guardarLimites(numeroTelefono)
            }
        )
    }
}


@Composable
fun TarjetaInfo(
    saldo: Float,
    limiteOnline: Float,
    limiteFisico: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A1D57))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Parte superior (Tipo de tarjeta y chip)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Débito",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )

                Image(
                    painter = painterResource(id = R.drawable.chip),
                    contentDescription = "Chip de tarjeta",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Parte central (Saldo)
            Text(
                text = "Saldo Tarjeta",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp
            )
            Text(
                text = "$${String.format("%.2f", saldo)}",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            // Parte inferior (Límites)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Límite físico",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "$${String.format("%.0f", limiteFisico)}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column {
                    Text(
                        text = "Límite online",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "$${String.format("%.0f", limiteOnline)}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun ModificarLimites(
    limiteCajeros: String,
    limiteCajerosCambio: (String) -> Unit,
    limiteComercio: String,
    limiteComercioCambios: (String) -> Unit,
    enviar: () -> Unit
) {
    Column {
        Text(
            text = "Limite diario cajeros",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = limiteCajeros,
            onValueChange = limiteCajerosCambio,
            label = { Text("1 -- 3.000") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Limite diario comercio",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = limiteComercio,
            onValueChange = limiteComercioCambios,
            label = { Text("1 -- 6.000") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = enviar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A1D57),
            )
        ) {
            Text(
                text = "Cambiar limites",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}