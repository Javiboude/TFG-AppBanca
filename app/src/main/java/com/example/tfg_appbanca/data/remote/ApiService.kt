package com.example.tfg_appbanca.data.remote


import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Contactos
import com.example.tfg_appbanca.data.model.RegisterResponse
import com.example.tfg_appbanca.data.model.gets.Ultimosmovimientos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("Contactos")
    fun getPersonajes(): Call<Contactos>

    @GET("BalanceDinero")
    fun getBalanceDinero(): Call<BalanceDinero>

    @GET("UltimosMovimientos")
    fun getUltimosMovimientos(): Call<Ultimosmovimientos>

    @POST("register")
    suspend fun registerUser(
        @Query("nombre") nombre: String,
        @Query("telefono") telefono: String,
        @Query("password") password: String
    ): Response<RegisterResponse>
}