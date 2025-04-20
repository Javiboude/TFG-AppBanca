package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaTransferenciaScreen(
    navController: NavController,
    pantallaTransferenciaViewModel : PantallaTransferenciaViewModel
) {
    // Collect state from ViewModel
    val iban = pantallaTransferenciaViewModel.iban
    val country = pantallaTransferenciaViewModel.country
    val recipientName = pantallaTransferenciaViewModel.recipientName
    val amount = pantallaTransferenciaViewModel.amount
    val concept = pantallaTransferenciaViewModel.concept
    val accountBalance = pantallaTransferenciaViewModel.accountBalance
    val isFormValid = pantallaTransferenciaViewModel.isFormValid
    val countries = pantallaTransferenciaViewModel.countries

    // Validate form when inputs change
    LaunchedEffect(iban, country, recipientName, amount) {
        pantallaTransferenciaViewModel.validateForm()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Account balance card (reutilizable)
        AccountBalanceCard(accountBalance)

        Spacer(modifier = Modifier.height(40.dp))

        // Transfer form
        TransferForm(
            iban = iban,
            onIbanChange = { pantallaTransferenciaViewModel.formatIban(it) },
            countries = countries,
            country = country,
            onCountryChange = { pantallaTransferenciaViewModel.country = it },
            recipientName = recipientName,
            onRecipientNameChange = { pantallaTransferenciaViewModel.recipientName = it },
            amount = amount,
            onAmountChange = { pantallaTransferenciaViewModel.amount = it },
            concept = concept,
            onConceptChange = { pantallaTransferenciaViewModel.concept = it },
            isFormValid = isFormValid,
            onSubmit = {
                pantallaTransferenciaViewModel.submitFirstPart()
                // Navegar a la segunda parte de la transferencia
                navController.navigate("transferencia_parte2")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransferForm(
    iban: String,
    onIbanChange: (String) -> Unit,
    countries: List<String>,
    country: String,
    onCountryChange: (String) -> Unit,
    recipientName: String,
    onRecipientNameChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    concept: String,
    onConceptChange: (String) -> Unit,
    isFormValid: Boolean,
    onSubmit: () -> Unit
) {
    Column {
        // IBAN Field
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

        // Country Dropdown
        Text(
            text = "País del destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        ExposedDropdownMenuBox(
            expanded = false, // Esto debería ser manejado con un estado
            onExpandedChange = {}
        ) {
            OutlinedTextField(
                value = country,
                onValueChange = {},
                label = { Text("Selecciona país") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                }
            )
            ExposedDropdownMenu(
                expanded = false,
                onDismissRequest = {}
            ) {
                countries.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onCountryChange(selectionOption)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recipient Name
        Text(
            text = "Nombre del destinatario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = recipientName,
            onValueChange = onRecipientNameChange,
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amount
        Text(
            text = "Importe",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = amount,
            onValueChange = onAmountChange,
            label = { Text("Cantidad a transferir") },
            modifier = Modifier.fillMaxWidth(),
            prefix = { Text("€") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Concept (optional)
        Text(
            text = "Concepto (opcional)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = concept,
            onValueChange = onConceptChange,
            label = { Text("Concepto de la transferencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Submit Button
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
                text = "Continuar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Reutilizamos el mismo componente de tarjeta de saldo
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