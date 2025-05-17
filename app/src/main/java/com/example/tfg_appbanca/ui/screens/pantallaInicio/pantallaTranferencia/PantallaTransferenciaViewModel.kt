package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaTransferenciaViewModel @Inject constructor(
    private val pantallaTransferenciaViewModel: GetRepository
) : ViewModel() {

    var iban by mutableStateOf("")
    var pais by mutableStateOf("")
    var personaDestinatario by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var concepto by mutableStateOf("")


    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaTransferenciaViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }
}