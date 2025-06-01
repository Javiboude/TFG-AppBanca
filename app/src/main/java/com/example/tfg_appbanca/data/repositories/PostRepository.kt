package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.registro.RegisterResponse
import com.example.tfg_appbanca.data.model.login.LoginRequest
import com.example.tfg_appbanca.data.model.login.LoginResponse
import com.example.tfg_appbanca.data.model.movimientos.AñadirDinero
import com.example.tfg_appbanca.data.model.movimientos.BizumRequest
import com.example.tfg_appbanca.data.model.movimientos.OperacionResponse
import com.example.tfg_appbanca.data.model.movimientos.TransferenciaRequest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {

    suspend fun registerUser(nombre: String, telefono: String, password: String, dinero: Float): RegisterResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.registerUser(nombre, telefono, password, dinero)

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
    suspend fun loginUser(telefono: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(telefono, password)
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

    suspend fun realizarBizum(
        telefonoEmisor: String,
        telefonoReceptor: String,
        cantidad: Float,
        concepto: String
    ): OperacionResponse? {
        return try {
            val request = BizumRequest(
                telefono_emisor = telefonoEmisor,
                telefono_receptor = telefonoReceptor,
                cantidad = cantidad,
                concepto = concepto
            )
            val response = RetrofitInstance.api.realizarBizum(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun realizarTransferencia(
        iban_emisor: String,
        iban_receptor: String,
        cantidad: Float,
        concepto: String
    ): OperacionResponse? {
        return try {
            val request = TransferenciaRequest(
                iban_emisor = iban_emisor,
                iban_receptor = iban_receptor,
                cantidad = cantidad,
                concepto = concepto
            )
            val response = RetrofitInstance.api.realizarTransferencia(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun añadirDinero(telefono: String, cantidad: Float): OperacionResponse? {
        return try {
            val request = AñadirDinero(
                telefono = telefono,
                cantidad = cantidad
            )
            val response = RetrofitInstance.api.añadirDinero(request)
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