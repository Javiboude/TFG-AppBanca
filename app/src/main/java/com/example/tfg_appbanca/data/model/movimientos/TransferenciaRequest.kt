package com.example.tfg_appbanca.data.model.movimientos

data class TransferenciaRequest (
    val iban_emisor: String,
    val iban_receptor: String,
    val cantidad: Float,
    val concepto: String
)