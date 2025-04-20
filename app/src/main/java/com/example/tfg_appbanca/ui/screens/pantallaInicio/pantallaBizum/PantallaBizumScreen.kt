package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    // Collect state from ViewModel
    val phoneNumber = pantallaBizumViewModel.phoneNumber
    val amount = pantallaBizumViewModel.amount
    val concept = pantallaBizumViewModel.concept
    val accountBalance = pantallaBizumViewModel.accountBalance
    val isFormValid = pantallaBizumViewModel.isFormValid

    // Validate form when inputs change
    LaunchedEffect(phoneNumber, amount) {
        pantallaBizumViewModel.validateForm()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Account balance card
        AccountBalanceCard(accountBalance)

        Spacer(modifier = Modifier.height(60.dp))

        // Bizum form
        BizumForm(
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { pantallaBizumViewModel.phoneNumber = it },
            amount = amount,
            onAmountChange = { pantallaBizumViewModel.amount = it },
            concept = concept,
            onConceptChange = { pantallaBizumViewModel.concept = it },
            isFormValid = isFormValid,
            onSubmit = {
                pantallaBizumViewModel.submitBizum()
                navController.navigate("${Destinations.PANTALLA_INICIO_URL}")
            }
        )
    }
}

@Composable
private fun AccountBalanceCard(balance: String) {
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
                text = "$$balance",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun BizumForm(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    concept: String,
    onConceptChange: (String) -> Unit,
    isFormValid: Boolean,
    onSubmit: () -> Unit
) {
    Column {
        Text(
            text = "Destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
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
            value = amount,
            onValueChange = onAmountChange,
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
            value = concept,
            onValueChange = onConceptChange,
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
            enabled = isFormValid
        ) {
            Text(
                text = "Confirmar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}