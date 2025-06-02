package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import com.example.tfg_appbanca.data.repositories.PutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaModificarLimitesViewModel @Inject constructor(
    private val repositoryGet: GetRepository,
    private val repositoryPut: PutRepository
) : ViewModel() {

    var limiteCajeros by mutableStateOf("")
    var limiteComercio by mutableStateOf("")

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _infoTarjeta = MutableStateFlow<InfoTarjeta?>(null)
    val infoTarjeta: StateFlow<InfoTarjeta?> = _infoTarjeta

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    var cambiarLimitesExitoso = mutableStateOf(false)
    var cambiarLimitesFallido = mutableStateOf(false)

    fun setLimitesExitoso(valor: Boolean) {
        cambiarLimitesExitoso.value = valor
    }

    fun setLimitesFallido(valor: Boolean) {
        cambiarLimitesFallido.value = valor
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = repositoryGet.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }

    suspend fun cargarInfoTarjeta(numeroTelefono: String) {
        val infoTarjeta = repositoryGet.fetchInfoTarjeta(numeroTelefono)
        _infoTarjeta.value = infoTarjeta
    }


     fun guardarLimites(numeroTelefono: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val limiteFisico = limiteCajeros.toDoubleOrNull()
            val limiteOnline = limiteComercio.toDoubleOrNull()
            val response = limiteFisico?.let {
                if (limiteOnline != null) {
                    repositoryPut.actualizarLimitesTarjeta(
                        telefono = numeroTelefono,
                        limiteOnline = limiteOnline,
                        limiteFisico = it
                    )
                }
            }
            if (response != null) {
                cambiarLimitesExitoso.value = true
            }else{
                cambiarLimitesFallido.value = true
            }
        }
    }
}