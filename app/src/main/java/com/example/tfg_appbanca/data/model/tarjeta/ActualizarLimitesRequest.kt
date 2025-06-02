package com.example.tfg_appbanca.data.model.tarjeta

data class ActualizarLimitesRequest(
    val telefono: String,
    val limite_online: Double,
    val limite_fisico: Double
)