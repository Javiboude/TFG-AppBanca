package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PantallaBizumScreen(
    navController: NavController,
    viewModel: PantallaBizumViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val numeroTelefonoUsuario by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()

    val bizumExitoso by viewModel.bizumExitoso
    val bizumFallido by viewModel.bizumFallido


    if (bizumExitoso) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    viewModel.setBizumExitoso(false)
                    navController.navigate(Destinations.PANTALLA_INICIO_URL)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Bizum exitoso") },
            text = { Text("Se ha enviado el dinero correctamente.") }
        )
    }
    if (bizumFallido == true) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    viewModel.setBizumFallido(false)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Bizum Fallido") },
            text = { Text("No se ha enviado el bizum.") }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getUsuarioInfo(numeroTelefonoUsuario)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        if (viewModel.usuario.value != null) {
            TarjetaCuenta(viewModel.usuario.value!!.dinero)
        }

        Spacer(modifier = Modifier.height(60.dp))

        Bizum(
            numeroTelefono = viewModel.numeroTelefono,
            onNumeroTelefonoCambio = { viewModel.numeroTelefono = it },
            cantidad = viewModel.cantidad,
            onCantidadCambio = { viewModel.cantidad = it },
            concepto = viewModel.concepto,
            onConceptoCambio = { viewModel.concepto = it },
            onSubmit = { viewModel.realizarBizum(numeroTelefonoUsuario) }
        )
    }
}

@Composable
private fun TarjetaCuenta(saldoCuenta: Float) {
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

    val camposValidos = numeroTelefono.isNotBlank() && cantidad.isNotBlank()

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
            modifier = Modifier.fillMaxWidth(),
            prefix = { Text("+34") }
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
            modifier = Modifier.fillMaxWidth(),
            prefix = { Text("€") }
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
                disabledContainerColor = Color(0xFFCCCCCC)
            ),
            enabled = camposValidos
        ) {
            Text(
                text = "Confirmar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}