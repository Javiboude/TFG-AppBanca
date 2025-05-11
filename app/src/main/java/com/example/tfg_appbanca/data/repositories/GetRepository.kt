package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Contactos
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.Ultimosmovimientos
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepository{
     suspend fun fetchContactos(): Contactos? {
         return withContext(Dispatchers.IO) {
             val response = RetrofitInstance.api.getPersonajes().execute()
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

    suspend fun fetchUltimosMovimientos(): Ultimosmovimientos? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getUltimosMovimientos().execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    suspend fun fetchInfoTarjeta(): InfoTarjeta? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getInfoTarjeta().execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    suspend fun getInfoPersonajeByNumeroTelefono(numeroTelefono: String): datosUsuario? {
        return withContext(Dispatchers.IO) {
            val response =
                RetrofitInstance.api.getDatosUsuario(numeroTelefono).execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
 }
