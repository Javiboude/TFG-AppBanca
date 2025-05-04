package com.example.tfg_appbanca.data.model.gets

data class Movimiento(
    val cantidad: Double,
    val esPositiva: Boolean,
    val id: Int,
    val lugar: String,
    val motivo: String
)