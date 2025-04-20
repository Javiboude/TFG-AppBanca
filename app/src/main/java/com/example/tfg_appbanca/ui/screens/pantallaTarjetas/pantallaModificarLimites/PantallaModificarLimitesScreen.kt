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
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R

@Composable
fun PantallaModificarLimites(
    navController: NavController,
    pantallaModificarLimitesViewModel: PantallaModificarLimitesViewModel
) {

    val limiteCajeros = pantallaModificarLimitesViewModel.limiteCajeros
    val limiteComercio = pantallaModificarLimitesViewModel.limiteComercio
    val saldoCuenta = pantallaModificarLimitesViewModel.saldoCuenta

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TarjetaInfo(saldoCuenta)

        ModificarLimites(
            limiteCajeros = limiteCajeros,
            limiteCajerosCambio = { pantallaModificarLimitesViewModel.limiteCajeros = it },
            limiteComercio = limiteComercio,
            limiteComercioCambios = { pantallaModificarLimitesViewModel.limiteComercio = it },
            enviar = {
                navController.navigate("${Destinations.PANTALLA_TARJETAS_URL}")
            }
        )
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
                    text = "Saldo Tarjeta",
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