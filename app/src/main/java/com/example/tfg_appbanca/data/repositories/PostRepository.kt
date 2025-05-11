package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.RegisterResponse
import com.example.tfg_appbanca.data.model.login.LoginRequest
import com.example.tfg_appbanca.data.model.login.LoginResponse

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

    // Login de usuario
    suspend fun loginUser(telefono: String, password: String, dinero: Float): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(telefono, password, dinero)
                val response = RetrofitInstance.api.loginUser(loginRequest)

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