package com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaAñadirDinero

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AñadirDineroViewModel @Inject constructor(
    private val getRepository: GetRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    var cantidadDinero by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var operacionExitosa = mutableStateOf(false)
    var operacionFallida = mutableStateOf(false)
    var mensajeExito by mutableStateOf("")
    var mensajeError by mutableStateOf("")

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    fun onCantidadDineroChange(nuevoValor: String) {
        cantidadDinero = nuevoValor
    }

    fun setOperacionExitosa(valor: Boolean) {
        operacionExitosa.value = valor
    }

    fun setOperacionFallida(valor: Boolean) {
        operacionFallida.value = valor
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        try {
            isLoading = true
            val result = getRepository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
            _usuario.emit(result)
        } catch (e: Exception) {
            mensajeError = "Error al obtener información del usuario"
            operacionFallida.value = true
        } finally {
            isLoading = false
        }
    }

    fun añadirDinero(telefono: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                operacionExitosa.value = false
                operacionFallida.value = false

                val cantidad = cantidadDinero.toFloatOrNull() ?: run {
                    mensajeError = "Ingrese una cantidad válida"
                    operacionFallida.value = true
                    return@launch
                }

                val response = postRepository.añadirDinero(
                    telefono = telefono,
                    cantidad = cantidad
                )

                if (response != null) {
                    mensajeExito = response.message
                    operacionExitosa.value = true
                    cantidadDinero = ""
                } else {
                    mensajeError = "Error al procesar la operación"
                    operacionFallida.value = true
                }
            } catch (e: Exception) {
                mensajeError = "Error de conexión: ${e.message}"
                operacionFallida.value = true
            } finally {
                isLoading = false
            }
        }
    }
}