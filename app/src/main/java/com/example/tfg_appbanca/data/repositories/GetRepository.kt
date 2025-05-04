package com.example.tfg_appbanca.data.repositories

import com.example.tfg_appbanca.data.model.BalanceDinero
import com.example.tfg_appbanca.data.model.Contactos
import com.example.tfg_appbanca.data.model.Ultimosmovimientos
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
 }
