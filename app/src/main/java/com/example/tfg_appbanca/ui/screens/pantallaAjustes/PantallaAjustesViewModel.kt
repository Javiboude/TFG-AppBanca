package com.example.tfg_appbanca.ui.screens.pantallaAjustes

import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaAjustesViewModel @Inject constructor(
    private val pantallaAjustesViewModel: GetRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaAjustesViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }
}
