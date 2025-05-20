package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaModificarLimitesViewModel  @Inject constructor(
    private val pantallaModificarLimitesViewModel: GetRepository
) : ViewModel() {


    var limiteCajeros by mutableStateOf("")
    var limiteComercio by mutableStateOf("")

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _infoTarjeta = MutableStateFlow<InfoTarjeta?>(null)
    val infoTarjeta: StateFlow<InfoTarjeta?> = _infoTarjeta


    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaModificarLimitesViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }

    suspend fun cargarInfoTarjeta(numeroTelefono: String) {
        val infoTarjeta = pantallaModificarLimitesViewModel.fetchInfoTarjeta(numeroTelefono)
        _infoTarjeta.value = infoTarjeta
    }
}