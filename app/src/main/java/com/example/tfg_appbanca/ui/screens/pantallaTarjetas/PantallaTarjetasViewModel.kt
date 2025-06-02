package com.example.tfg_appbanca.ui.screens.pantallaTarjetas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.gets.Movimiento
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

@HiltViewModel
class PantallaTarjetasViewModel @Inject constructor(
    private val pantallaTarjetasViewModel: GetRepository
) : ViewModel() {

    private val _ultimosMovimientos = MutableStateFlow<List<Movimiento>>(emptyList())
    val ultimosMovimientos: StateFlow<List<Movimiento>> = _ultimosMovimientos

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario


    suspend fun cargarDatosUsuario(numeroTelefono: String) {
        try {

            val usuarioInfo = pantallaTarjetasViewModel.getInfoPersonajeByNumeroTelefono(numeroTelefono)
            _usuario.value = usuarioInfo

            usuarioInfo?.let { usuario ->

                val movimientosDeferred = viewModelScope.async {
                    pantallaTarjetasViewModel.fetchUltimosMovimientos(usuario.id)?.movimientos ?: emptyList()
                }
                _ultimosMovimientos.value = movimientosDeferred.await()
            }
        } catch (e: Exception) {
            println("Error cargando datos: ${e.message}")
        }
    }
}
