package com.example.tfg_appbanca.ui.screens.pantallaInicio.pantallaTranferencia


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantallaTransferenciaViewModel : ViewModel() {

    var iban by mutableStateOf("")
    var pais by mutableStateOf("")
    var personaDestinatario by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var concepto by mutableStateOf("")


    val saldoCuenta = "103"

}