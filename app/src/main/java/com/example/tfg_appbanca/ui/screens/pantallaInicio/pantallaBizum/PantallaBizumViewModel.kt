package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

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
class PantallaBizumViewModel @Inject constructor(
    private val getRepository: GetRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    var numeroTelefono by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var concepto by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var successMessage by mutableStateOf("")

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    var bizumExitoso = mutableStateOf(false)
    var bizumFallido = mutableStateOf(false)

    fun setBizumExitoso(valor: Boolean) {
        bizumExitoso.value = valor
    }

    fun setBizumFallido(valor: Boolean) {
        bizumFallido.value = valor
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        try {
            isLoading = true
            val result = getRepository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
            if (result != null) {
                _usuario.emit(result)
            }
        } catch (e: Exception) {
            errorMessage = "Error al obtener información del usuario"
        } finally {
            isLoading = false
        }
    }

    fun realizarBizum(telefonoEmisor: String) {
        viewModelScope.launch {
            isLoading = true
            val cantidadFloat = cantidad.toFloatOrNull() ?: run {
                isLoading = false
                return@launch
            }

            val response = postRepository.realizarBizum(
                telefonoEmisor = telefonoEmisor,
                telefonoReceptor = numeroTelefono,
                cantidad = cantidadFloat,
                concepto = concepto
            )

            if (response != null) {
                successMessage = response.message
                getUsuarioInfo(telefonoEmisor)
                bizumExitoso.value = true
            } else {
                bizumFallido.value = true
            }
        }
    }
}