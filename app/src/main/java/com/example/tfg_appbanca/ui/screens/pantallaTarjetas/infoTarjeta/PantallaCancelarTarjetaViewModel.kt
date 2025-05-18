package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.infoTarjeta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaCancelarTarjetaViewModel @Inject constructor(
    private val pantallaCancelarTarjetaViewModel: GetRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _infoTarjeta = MutableStateFlow<InfoTarjeta?>(null)
    val infoTarjeta: StateFlow<InfoTarjeta?> = _infoTarjeta

    init {
        cargarInfoTarjeta()
    }

    private fun cargarInfoTarjeta() {
        viewModelScope.launch {
            val infoTarjeta = pantallaCancelarTarjetaViewModel.fetchInfoTarjeta()
            _infoTarjeta.value = infoTarjeta
        }
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaCancelarTarjetaViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }
}