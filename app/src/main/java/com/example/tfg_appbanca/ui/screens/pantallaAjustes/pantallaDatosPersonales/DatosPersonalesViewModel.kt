package com.example.tfg_appbanca.ui.screens.pantallaAjustes.pantallaDatosPersonales

import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DatosPersonalesViewModel @Inject constructor(
    private val getRepository: GetRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _infoTarjeta = MutableStateFlow<InfoTarjeta?>(null)
    val infoTarjeta: StateFlow<InfoTarjeta?> = _infoTarjeta


    suspend fun cargarInfoTarjeta(numeroTelefono: String) {
        val infoTarjeta = getRepository.fetchInfoTarjeta(numeroTelefono)
        _infoTarjeta.value = infoTarjeta
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = getRepository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }
}