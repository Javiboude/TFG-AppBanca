package com.example.tfg_appbanca.data.remote


import com.example.tfg_appbanca.data.model.BalanceDinero
import com.example.tfg_appbanca.data.model.Contactos
import com.example.tfg_appbanca.data.model.Ultimosmovimientos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Contactos")
    fun getPersonajes(): Call<Contactos>

    @GET("BalanceDinero")
    fun getBalanceDinero(): Call<BalanceDinero>

    @GET("UltimosMovimientos")
    fun getUltimosMovimientos(): Call<Ultimosmovimientos>
}