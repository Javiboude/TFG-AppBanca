package com.example.tfg_appbanca.ui.screens.pantallaTarjetas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.Movimiento
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class PantallaTarjetasViewModel @Inject constructor(
    private val pantallaTarjetasViewModel: GetRepository
) : ViewModel() {

    val saldoCuenta = "103.00"
    var ultimosMovimientos by mutableStateOf(listOf<Movimiento>())

    suspend fun cargarUltimosMovimientos(): List<Movimiento>{
        val movimientos = pantallaTarjetasViewModel.fetchUltimosMovimientos()?.let { infoPersonaje ->
            infoPersonaje.movimientos
        }

        if (movimientos != null) {
            this.ultimosMovimientos = movimientos
        }

        return this.ultimosMovimientos
    }

}
