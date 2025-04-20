package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations

@Composable
fun PantallaBizumScreen(
    navController: NavController,
    pantallaBizumViewModel : PantallaBizumViewModel
) {

    val numeroTelefono = pantallaBizumViewModel.numeroTelefono
    val cantidad = pantallaBizumViewModel.cantidad
    val concepto = pantallaBizumViewModel.concepto
    val saldoCuenta = pantallaBizumViewModel.saldoCuenta


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        TarjetaCuenta(saldoCuenta)

        Spacer(modifier = Modifier.height(60.dp))

        Bizum(
            numeroTelefono = numeroTelefono,
            onNumeroTelefonoCambio = { pantallaBizumViewModel.numeroTelefono = it },
            cantidad = cantidad,
            onCantidadCambio = { pantallaBizumViewModel.cantidad = it },
            concepto = concepto,
            onConceptoCambio = { pantallaBizumViewModel.concepto = it },
            onSubmit = {
                navController.navigate("${Destinations.PANTALLA_INICIO_URL}")
            }
        )
    }
}

@Composable
private fun TarjetaCuenta(saldoCuenta: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color(0xFF0A1D57),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Cuenta Safe Money",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$$saldoCuenta",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Bizum(
    numeroTelefono: String,
    onNumeroTelefonoCambio: (String) -> Unit,
    cantidad: String,
    onCantidadCambio: (String) -> Unit,
    concepto: String,
    onConceptoCambio: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column {
        Text(
            text = "Destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = onNumeroTelefonoCambio,
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Importe",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = cantidad,
            onValueChange = onCantidadCambio,
            label = { Text("Importe") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Concepto (opcional)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = concepto,
            onValueChange = onConceptoCambio,
            label = { Text("Concepto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A1D57),
            ),
        ) {
            Text(
                text = "Confirmar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}