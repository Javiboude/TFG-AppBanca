package com.example.tfg_appbanca.data.model.movimientos

data class BizumRequest(
    val telefono_emisor: String,
    val telefono_receptor: String,
    val cantidad: Float,
    val concepto: String
)