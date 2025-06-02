package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.tarjeta.ActualizarLimitesRequest
import com.example.tfg_appbanca.data.model.tarjeta.LimitesResponse


class PutRepository {
    suspend fun actualizarLimitesTarjeta(
        telefono: String,
        limiteOnline: Double,
        limiteFisico: Double
    ): LimitesResponse? {
        return try {
            val request = ActualizarLimitesRequest(
                telefono = telefono,
                limite_online = limiteOnline,
                limite_fisico = limiteFisico
            )
            val response = RetrofitInstance.api.actualizarLimites(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}