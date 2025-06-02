package com.example.tfg_appbanca.ui.screens.pantallaInicio


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Movimiento
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PantallaInicioViewModel @Inject constructor(
    private val pantallaInicioRepository: GetRepository
) : ViewModel() {

    private val _contactos = MutableStateFlow<List<String>>(emptyList())
    val contactos: StateFlow<List<String>> = _contactos

    private val _balanceDinero = MutableStateFlow<BalanceDinero?>(null)
    val balanceDinero: StateFlow<BalanceDinero?> = _balanceDinero

    private val _ultimosMovimientos = MutableStateFlow<List<Movimiento>>(emptyList())
    val ultimosMovimientos: StateFlow<List<Movimiento>> = _ultimosMovimientos

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        cargarBalance()
    }

    suspend fun cargarDatosUsuario(numeroTelefono: String) {
        _isLoading.value = true
        try {

            val usuarioInfo = pantallaInicioRepository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
            _usuario.value = usuarioInfo

            usuarioInfo?.let { usuario ->
                val contactosDeferred = viewModelScope.async {
                    pantallaInicioRepository.fetchContactos(usuario.id)?.contactos ?: emptyList()
                }

                val movimientosDeferred = viewModelScope.async {
                    pantallaInicioRepository.fetchUltimosMovimientos(usuario.id)?.movimientos ?: emptyList()
                }

                _contactos.value = contactosDeferred.await()
                _ultimosMovimientos.value = movimientosDeferred.await()
            }
        } catch (e: Exception) {
            println("Error cargando datos: ${e.message}")
        } finally {
            _isLoading.value = false
        }
    }

    private fun cargarBalance() {
        viewModelScope.launch {
            try {
                _balanceDinero.value = pantallaInicioRepository.fetchBalanceDinero()
            } catch (e: Exception) {
                println("Error cargando balance: ${e.message}")
            }
        }
    }
}