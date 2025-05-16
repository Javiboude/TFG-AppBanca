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

    var ultimosMovimientos by mutableStateOf(listOf<Movimiento>())

    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    init {
        cargarContactos()
        cargarBalance()
    }

    private fun cargarContactos() {
        viewModelScope.launch {
            val result = pantallaInicioRepository.fetchContactos()?.contactos ?: emptyList()
            _contactos.value = result
        }
    }

    private fun cargarBalance() {
        viewModelScope.launch {
            val balance = pantallaInicioRepository.fetchBalanceDinero()
            _balanceDinero.value = balance
        }
    }

    suspend fun cargarUltimosMovimientos(): List<Movimiento>{
        val movimientos = pantallaInicioRepository.fetchUltimosMovimientos()?.let { infoPersonaje ->
            infoPersonaje.movimientos
        }
        if (movimientos != null) {
            this.ultimosMovimientos = movimientos
        }
        return this.ultimosMovimientos
    }

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val result = pantallaInicioRepository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
        if (result != null) {
            _usuario.value = result
        }
    }
}
