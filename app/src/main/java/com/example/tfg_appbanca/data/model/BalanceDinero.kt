package com.example.tfg_appbanca.data.model

data class BalanceDinero(
    val balanceTotal: String,
    val gastos: List<Float>,
    val ingresos: List<Float>,
    val meses: List<String>
)