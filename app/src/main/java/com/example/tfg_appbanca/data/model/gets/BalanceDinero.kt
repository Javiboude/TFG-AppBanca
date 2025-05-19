package com.example.tfg_appbanca.data.model.gets

data class BalanceDinero(
    val gastos: List<Float>,
    val ingresos: List<Float>,
    val meses: List<String>
)