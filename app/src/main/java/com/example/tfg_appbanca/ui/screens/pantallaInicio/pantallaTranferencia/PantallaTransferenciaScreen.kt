package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

@Composable
fun PantallaTransferenciaScreen(
    navController: NavController,
    pantallaTransferenciaViewModel : PantallaTransferenciaViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val iban = pantallaTransferenciaViewModel.iban
    val pais = pantallaTransferenciaViewModel.pais
    val personaDestinatario = pantallaTransferenciaViewModel.personaDestinatario
    val cantidad = pantallaTransferenciaViewModel.cantidad
    val concepto = pantallaTransferenciaViewModel.concepto
    val usuario by pantallaTransferenciaViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefonoUsuario by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()

    val transferenciaExitosa by pantallaTransferenciaViewModel.transferenciaExitosa
    val transferenciaFallida by pantallaTransferenciaViewModel.transferenciaFallida


    if (transferenciaExitosa) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    pantallaTransferenciaViewModel.setTransferenciaExitosa(false)
                    navController.navigate(Destinations.PANTALLA_INICIO_URL)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Transferencia exitosa") },
            text = { Text("Se ha enviado el dinero correctamente.") }
        )
    }
    if (transferenciaFallida == true) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    pantallaTransferenciaViewModel.setRransferenciaFallida(false)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Transferencia fallida") },
            text = { Text("No se ha enviado la transferencia.") }
        )
    }

    LaunchedEffect(Unit) {
        pantallaTransferenciaViewModel.getUsuarioInfo(numeroTelefonoUsuario)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        if (usuario != null) {
            TarjetaCuenta(usuario!!.dinero)
        }

        Spacer(modifier = Modifier.height(40.dp))

        if (usuario != null) {
            TransferForm(
                camposValidos = pantallaTransferenciaViewModel.camposEstanCompletos(),
                iban = iban,
                onIbanChange = { pantallaTransferenciaViewModel.iban = it },
                pais = pais,
                onPaisCambio = { pantallaTransferenciaViewModel.pais = it },
                personaDestinatario = personaDestinatario,
                onPersonaDestinatarioCambio = {
                    pantallaTransferenciaViewModel.personaDestinatario = it
                },
                cantidad = cantidad,
                onCantidadCambio = { pantallaTransferenciaViewModel.cantidad = it },
                concepto = concepto,
                onConceptoCambio = { pantallaTransferenciaViewModel.concepto = it },
                onSubmit = {
                    pantallaTransferenciaViewModel.realizarTransferencia(usuario!!.iban)
                }
            )
        }
    }
}

@Composable
private fun TransferForm(
    camposValidos: Boolean,
    iban: String,
    onIbanChange: (String) -> Unit,
    pais: String,
    onPaisCambio: (String) -> Unit,
    personaDestinatario: String,
    onPersonaDestinatarioCambio: (String) -> Unit,
    cantidad: String,
    onCantidadCambio: (String) -> Unit,
    concepto: String,
    onConceptoCambio: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column {
        Text(
            text = "IBAN del destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = iban,
            onValueChange = onIbanChange,
            label = { Text("IBAN") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("ESXX XXXX XXXX XXXX XXXX XXXX") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "País del destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
            OutlinedTextField(
                value = pais,
                onValueChange = onPaisCambio,
                label = { Text("Selecciona país") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Pais") }
            )


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre del destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = personaDestinatario,
            onValueChange = onPersonaDestinatarioCambio,
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Importe",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = cantidad,
            onValueChange = onCantidadCambio,
            label = { Text("Cantidad a transferir") },
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
            label = { Text("Concepto de la transferencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = camposValidos,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A1D57),
                disabledContainerColor = Color(0xFFCCCCCC)
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


@Composable
private fun TarjetaCuenta(saldoCuenta: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
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