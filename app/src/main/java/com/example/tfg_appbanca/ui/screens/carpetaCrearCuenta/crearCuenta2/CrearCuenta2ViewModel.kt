package com.example.httpclienttest.ui.screens.crearCuenta2

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.RegisterResponse
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrearCuenta2ViewModel @Inject constructor(
    private val pantallaCrearCuenta2: PostRepository
) : ViewModel() {


    val nombre = mutableStateOf("")
    val numeroTelefono = mutableStateOf("")
    val contraseña = mutableStateOf("")
    val confirmarContraseña = mutableStateOf("")
    val contraseñaVisible = mutableStateOf(false)
    val registerResponse = mutableStateOf<RegisterResponse?>(null)
    val isLoading = mutableStateOf(false)

    fun togglePasswordVisibility() {
        contraseñaVisible.value = !contraseñaVisible.value
    }

    fun registerUser(nombre: String, numeroTelefono: String, contraseña: String) {

        if (nombre.isNotEmpty() && numeroTelefono.isNotEmpty() && contraseña.isNotEmpty()) {
            isLoading.value = true
            viewModelScope.launch {
                val response = pantallaCrearCuenta2.registerUser(nombre, numeroTelefono, contraseña)
                isLoading.value = false
                registerResponse.value = response
            }
        }
    }
}