package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia


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
class PantallaTransferenciaViewModel @Inject constructor(
    private val pantallaTransferenciaViewModel: GetRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    var iban by mutableStateOf("")
    var pais by mutableStateOf("")
    var personaDestinatario by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var concepto by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var successMessage by mutableStateOf("")


    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    var transferenciaExitosa = mutableStateOf(false)
    var transferenciaFallida = mutableStateOf(false)

    fun setTransferenciaExitosa(valor: Boolean) {
        transferenciaExitosa.value = valor
    }

    fun setRransferenciaFallida(valor: Boolean) {
        transferenciaFallida.value = valor
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaTransferenciaViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }

    fun realizarTransferencia(iban_emisor: String) {
        viewModelScope.launch {
            isLoading = true
            val cantidadFloat = cantidad.toFloatOrNull() ?: run {
                isLoading = false
                return@launch
            }

            val response = postRepository.realizarTransferencia(
                iban_emisor = iban_emisor,
                iban_receptor = iban,
                cantidad = cantidadFloat,
                concepto = concepto
            )

            if (response != null) {
                successMessage = response.message
                getUsuarioInfo(iban_emisor)
                transferenciaExitosa.value = true
            } else {
                transferenciaFallida.value = true
            }
        }
    }
    fun camposEstanCompletos(): Boolean {
        return iban.isNotBlank() &&
                pais.isNotBlank() &&
                personaDestinatario.isNotBlank() &&
                cantidad.isNotBlank()
    }
}