package com.example.tfg_appbanca.ui.screens.patallaLogin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.login.LoginResponse
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaLoginViewModel @Inject constructor(
    private val pantallaLoginViewModel: PostRepository
) : ViewModel() {

    val numeroTelefono = mutableStateOf("")
    val contraseña = mutableStateOf("")
    val cantidadDinero = mutableStateOf("")
    val contraseñaVisible = mutableStateOf(false)
    val registerResponse = mutableStateOf<LoginResponse?>(null)

    var loginFallido = mutableStateOf(false)
    var loginExitoso = mutableStateOf(false)


    fun setRegistroFallido(valor: Boolean) {
        loginFallido.value = valor
    }

    fun setLoginExitoso(valor: Boolean) {
        loginExitoso.value = valor
    }

    fun togglePasswordVisibility() {
        contraseñaVisible.value = !contraseñaVisible.value
    }

    fun login() {
        val telefono = numeroTelefono.value
        val password = contraseña.value
        val cantidadDinero = cantidadDinero.value.toFloatOrNull() ?: 0F

        viewModelScope.launch {
            val respuesta = pantallaLoginViewModel.loginUser(telefono, password, cantidadDinero)
            registerResponse.value = respuesta
            if (respuesta != null) {
                if (respuesta.user_id != 0) {
                    loginExitoso.value = true
                }else{
                    loginFallido.value = true
                }
            }else{
                loginFallido.value = true
            }
        }
    }


}