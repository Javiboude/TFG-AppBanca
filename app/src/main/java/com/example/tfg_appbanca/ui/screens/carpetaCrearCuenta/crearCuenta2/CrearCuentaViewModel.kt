package com.example.httpclienttest.ui.screens.crearCuenta2

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.registro.RegisterResponse
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrearCuentaViewModel @Inject constructor(
    private val pantallaCrearCuenta2: PostRepository
) : ViewModel() {


    val nombre = mutableStateOf("")
    val numeroTelefono = mutableStateOf("")
    val contraseña = mutableStateOf("")
    val confirmarContraseña = mutableStateOf("")
    val contraseñaVisible = mutableStateOf(false)
    val cantidadDinero = mutableStateOf("")
    val registerResponse = mutableStateOf<RegisterResponse?>(null)
    val isLoading = mutableStateOf(false)

    var registroExitoso = mutableStateOf(false)
    var registroFallido = mutableStateOf(false)


    fun setRegistroExitoso(valor: Boolean) {
        registroExitoso.value = valor
    }

    fun setRegistroFallido(valor: Boolean) {
        registroFallido.value = valor
    }

    fun togglePasswordVisibility() {
        contraseñaVisible.value = !contraseñaVisible.value
    }

    fun registerUser(nombre: String, numeroTelefono: String, contraseña: String, dinero: String) {
        if (nombre.isNotEmpty() && numeroTelefono.isNotEmpty() && contraseña.isNotEmpty() && dinero.isNotEmpty()) {

            val dineroFloat = try {
                dinero.toFloat()
            } catch (e: NumberFormatException) {
                0f
            }

            isLoading.value = true
            viewModelScope.launch {
                val response = pantallaCrearCuenta2.registerUser(nombre, numeroTelefono, contraseña, dineroFloat)
                isLoading.value = false
                registerResponse.value = response
                if (response != null) {
                    if (response.user_id != 0) {
                        registroExitoso.value = true
                    } else {
                        registroFallido.value = true
                    }
                }else{
                    registroFallido.value = true
                }
            }
        }
    }
}