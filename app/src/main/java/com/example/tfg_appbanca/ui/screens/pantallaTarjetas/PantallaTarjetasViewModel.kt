package com.example.tfg_appbanca.ui.screens.pantallaTarjetas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.Movimiento
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

@HiltViewModel
class PantallaTarjetasViewModel @Inject constructor(
    private val pantallaTarjetasViewModel: GetRepository
) : ViewModel() {

    var ultimosMovimientos by mutableStateOf(listOf<Movimiento>())

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario



    suspend fun cargarUltimosMovimientos(): List<Movimiento> {
        val movimientos =
            pantallaTarjetasViewModel.fetchUltimosMovimientos()?.let { infoPersonaje ->
                infoPersonaje.movimientos
            }

        if (movimientos != null) {
            this.ultimosMovimientos = movimientos
        }

        return this.ultimosMovimientos
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaTarjetasViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.emit(result)
        }
    }
}
