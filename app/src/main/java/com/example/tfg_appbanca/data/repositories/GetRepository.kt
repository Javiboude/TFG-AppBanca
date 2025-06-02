package com.example.tfg_appbanca.data.repositories

import android.annotation.SuppressLint
import android.util.Log
import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Contactos
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.Ultimosmovimientos
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepository{
     suspend fun fetchContactos(id: Int): Contactos? {
         return withContext(Dispatchers.IO) {
             val response = RetrofitInstance.api.getPersonajes(id).execute()
             if (response.isSuccessful) {
                 response.body()
             } else {
                 null
             }
         }
     }

    suspend fun fetchBalanceDinero(): BalanceDinero? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getBalanceDinero().execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    suspend fun fetchUltimosMovimientos(id: Int): Ultimosmovimientos? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getUltimosMovimientos(id).execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun fetchInfoTarjeta(numeroTelefono: String): InfoTarjeta? {
        return withContext(Dispatchers.IO) {
            try {
            val response = RetrofitInstance.api.getInfoTarjeta(numeroTelefono)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getInfoPersonajeByNumeroTelefono(numeroTelefono: String): datosUsuario? {
        return withContext(Dispatchers.IO) {
            try {
                // Ahora la respuesta es directamente datosUsuario
                val response = RetrofitInstance.api.getDatosUsuario(numeroTelefono)
                Log.d("APIResponse", "Respuesta API: $response")
                response
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
