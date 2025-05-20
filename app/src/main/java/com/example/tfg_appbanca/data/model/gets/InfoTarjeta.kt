package com.example.tfg_appbanca.data.model.gets

data class InfoTarjeta(
    val cuentaAsociada: String,
    val cvc: String,
    val fechaCaducidad: String,
    val tipo: String,
    val numeroTarjeta: String,
    val limiteOnline: Float,
    val limiteFisico: Float
)