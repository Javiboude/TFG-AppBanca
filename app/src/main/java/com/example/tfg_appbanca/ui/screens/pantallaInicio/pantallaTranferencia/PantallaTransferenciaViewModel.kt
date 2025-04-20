package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantallaTransferenciaViewModel : ViewModel() {
    // State for the form fields
    var iban by mutableStateOf("")
    var country by mutableStateOf("")
    var recipientName by mutableStateOf("")
    var amount by mutableStateOf("")
    var concept by mutableStateOf("")

    // Validation state
    var isFormValid by mutableStateOf(false)

    // Account balance
    val accountBalance = "103" // Esto debería venir de una fuente de datos real

    // Lista de países (simplificado)
    val countries = listOf("España", "Portugal", "Francia", "Alemania", "Italia")

    // Function to validate form
    fun validateForm() {
        isFormValid = iban.isNotBlank() &&
                country.isNotBlank() &&
                recipientName.isNotBlank() &&
                amount.isNotBlank() &&
                amount.toDoubleOrNull() != null
    }

    // Function to handle IBAN formatting/validation
    fun formatIban(input: String) {
        // Eliminar espacios y letras no válidas
        val cleanedInput = input.filter { it.isLetterOrDigit() }.uppercase()
        iban = cleanedInput.chunked(4).joinToString(" ")
    }

    // Function to handle form submission (primera parte)
    fun submitFirstPart() {
        if (isFormValid) {
            // Guardar los datos y prepararse para la segunda parte
        }
    }
}