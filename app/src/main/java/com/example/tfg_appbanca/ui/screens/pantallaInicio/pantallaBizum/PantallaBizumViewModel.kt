package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaBizum

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantallaBizumViewModel : ViewModel() {

    var numeroTelefono by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var concepto by mutableStateOf("")


    // Account balance
    val saldoCuenta = "103"

}