package com.example.tfg_appbanca.data.model.gets

data class InfoTarjeta(
    val cuentaAsociada: String,
    val cvc: String,
    val fechaCaducidad: String,
    val saldoCuenta: String,
    val tipo: String,
    val titular: String
)