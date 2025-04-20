package com.example.tfg_appbanca.ui.screens.pantallaTarjetas.pantallaModificarLimites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantallaModificarLimitesViewModel : ViewModel() {

    var limiteCajeros by mutableStateOf("")
    var limiteComercio by mutableStateOf("")

    val saldoCuenta = "103"
}