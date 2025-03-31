package com.example.httpclienttest.ui.screens.crearCuenta2

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class CrearCuenta2ViewModel : ViewModel() {
    // Estado para los campos de entrada
    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val isPasswordVisible = mutableStateOf(false)

    // Función para alternar la visibilidad de la contraseña
    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }
}