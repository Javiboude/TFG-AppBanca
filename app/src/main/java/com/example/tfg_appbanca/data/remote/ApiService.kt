package com.example.tfg_appbanca.data.remote


import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Contactos
import com.example.tfg_appbanca.data.model.RegisterResponse
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.Ultimosmovimientos
import com.example.tfg_appbanca.data.model.registro.LoginRequest
import com.example.tfg_appbanca.data.model.registro.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
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

    @GET("Infotarjeta")
    fun getInfoTarjeta(): Call<InfoTarjeta>

    @POST("register")
    suspend fun registerUser(
        @Query("nombre") nombre: String,
        @Query("telefono") telefono: String,
        @Query("password") password: String
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>
}