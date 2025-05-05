package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.infoTarjeta

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun PantallaCancelarTarjetaScreen(
    navController: NavController,
    pantallaCancelarTarjetaViewModel: PantallaCancelarTarjetaViewModel = hiltViewModel()
) {
    val saldoCuenta = pantallaCancelarTarjetaViewModel.saldoCuenta
    val infoTarjeta by pantallaCancelarTarjetaViewModel.infoTarjeta.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TarjetaInfo(saldoCuenta)

        Spacer(modifier = Modifier.height(16.dp))

        infoTarjeta?.let {
            InfoTarjeta(
                titular = it.titular,
                cuentaAsociada = it.cuentaAsociada,
                fechaCaducidad = it.fechaCaducidad,
                cvc = it.cvc,
                tipo = it.tipo,
                enviar = {
                    navController.navigate("${Destinations.PANTALLA_TARJETAS_URL}")
                }
            )
        }
    }
}

@Composable
fun TarjetaInfo(saldo: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A1D57))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Débito",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.chip),
                    contentDescription = "Icono de chip",
                    modifier = Modifier.size(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Balance Tarjeta",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$$saldo",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun InfoTarjeta(
    titular: String,
    cuentaAsociada: String,
    fechaCaducidad: String,
    cvc: String,
    tipo: String,
    enviar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        InfoItem(titulo = "Titular", valor = titular)
        InfoItem(titulo = "Cuenta asociada", valor = cuentaAsociada)
        InfoItem(titulo = "Fecha de caducidad", valor = fechaCaducidad)
        InfoItem(titulo = "CVC", valor = cvc)
        InfoItem(titulo = "Tipo", valor = tipo)

        Spacer(modifier = Modifier.height(24.dp))

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
                text = "Siguiente",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun InfoItem(titulo: String, valor: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = titulo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = valor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
