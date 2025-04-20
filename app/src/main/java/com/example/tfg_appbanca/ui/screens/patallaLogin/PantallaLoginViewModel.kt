package com.example.httpclienttest.ui.screens.patallaLogin

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class PantallaLoginViewModel : ViewModel() {
    // Estado para los campos de entrada
    val phoneNumber = mutableStateOf("")
    val password = mutableStateOf("")
    val amount = mutableStateOf("")
    val isPasswordVisible = mutableStateOf(false)

    // Función para alternar la visibilidad de la contraseña
    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }

    // Función para navegar al registro (ejemplo)
    fun navigateToRegister() {
        // Implementa la lógica para navegar al registro aquí
    }
}