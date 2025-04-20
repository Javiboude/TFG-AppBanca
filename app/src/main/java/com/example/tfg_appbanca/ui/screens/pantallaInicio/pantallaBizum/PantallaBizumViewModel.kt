package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantallaBizumViewModel : ViewModel() {
    // State for the form fields
    var phoneNumber by mutableStateOf("")
    var amount by mutableStateOf("")
    var concept by mutableStateOf("")

    // Validation state
    var isFormValid by mutableStateOf(false)

    // Account balance
    val accountBalance = "103"

    // Function to validate form
    fun validateForm() {
        isFormValid = phoneNumber.isNotBlank() &&
                amount.isNotBlank() &&
                amount.toDoubleOrNull() != null
    }

    // Function to handle form submission
    fun submitBizum() {
        if (isFormValid) {
            // Here you would typically make an API call to process the Bizum
            // For now, we just validate and navigate on button click
        }
    }
}