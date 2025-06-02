package com.example.tfg_appbanca.data.model.tarjeta

data class LimitesResponse(
    val message: String,
    val numeroTarjeta: String,
    val nuevoLimiteOnline: Double,
    val nuevoLimiteFisico: Double
)