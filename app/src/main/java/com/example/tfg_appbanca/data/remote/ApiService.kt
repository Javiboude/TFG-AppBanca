package com.example.tfg_appbanca.data.remote


import com.example.tfg_appbanca.data.model.gets.BalanceDinero
import com.example.tfg_appbanca.data.model.gets.Contactos
import com.example.tfg_appbanca.data.model.registro.RegisterResponse
import com.example.tfg_appbanca.data.model.gets.InfoTarjeta
import com.example.tfg_appbanca.data.model.gets.Ultimosmovimientos
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.model.login.LoginRequest
import com.example.tfg_appbanca.data.model.login.LoginResponse
import com.example.tfg_appbanca.data.model.movimientos.AñadirDinero
import com.example.tfg_appbanca.data.model.movimientos.BizumRequest
import com.example.tfg_appbanca.data.model.movimientos.OperacionResponse
import com.example.tfg_appbanca.data.model.movimientos.TransferenciaRequest
import com.example.tfg_appbanca.data.model.tarjeta.ActualizarLimitesRequest
import com.example.tfg_appbanca.data.model.tarjeta.LimitesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("contactos/{user-id}")
    fun getPersonajes(@Path("user-id") id: Int): Call<Contactos>

    @GET("balance-dinero")
    fun getBalanceDinero(): Call<BalanceDinero>

    @GET("ultimos-movimientos/{user-id}")
    fun getUltimosMovimientos(@Path("user-id") id: Int): Call<Ultimosmovimientos>

    @GET("info-tarjeta/{telefono}")
    suspend fun getInfoTarjeta(@Path("telefono") telefono: String): InfoTarjeta

    @POST("register")
    suspend fun registerUser(
        @Query("nombre") nombre: String,
        @Query("telefono") telefono: String,
        @Query("password") password: String,
        @Query("dinero") dinero: Float,
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @GET("datos-usuario/{telefono}")
    suspend fun getDatosUsuario(@Path("telefono") telefono: String): datosUsuario

    @POST("realizar-bizum")
    suspend fun realizarBizum(
        @Body request: BizumRequest
    ): Response<OperacionResponse>

    @POST("realizar-transferencia")
    suspend fun realizarTransferencia(
        @Body request: TransferenciaRequest
    ): Response<OperacionResponse>

    @POST("anadir-dinero")
    suspend fun añadirDinero(
        @Body request: AñadirDinero
    ): Response<OperacionResponse>

    @PUT("actualizar-limites-tarjeta")
    suspend fun actualizarLimites(@Body request: ActualizarLimitesRequest): Response<LimitesResponse>
}