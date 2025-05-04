package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {

    suspend fun registerUser(nombre: String, telefono: String, password: String): RegisterResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.registerUser(nombre, telefono, password)

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
}