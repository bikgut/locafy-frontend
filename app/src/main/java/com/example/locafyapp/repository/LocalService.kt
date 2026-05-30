package com.example.locafyapp.repository

import com.example.locafyapp.models.LocalesModel
import com.example.locafyapp.models.LoginModel
import com.example.locafyapp.security.AuthInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface LocalService {

    companion object{

        val clienteOkHttp = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        val instance =
            //AQUI VA LA IPV4
            Retrofit.Builder().baseUrl("http://192.168.1.105:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(clienteOkHttp).build()
                .create(LocalService::class.java)
    }

    @GET("local")
    suspend fun listarLocales():List<LocalesModel>

    @GET("local/{id}")
    suspend fun obtenerPorId(@Path("id") codigo:String): LocalesModel

    @POST("productos")
    suspend fun agregarLocal(@Body local : LocalesModel)

    @PUT("locales")
    suspend fun actualizarLocal(@Body local : LocalesModel)

    @DELETE("local/{id}")
    suspend fun eliminarLocal(@Path("codigo") codigo : String)

    @GET("local/buscar")
    suspend fun buscarLocales(@Query("q") termino: String): List<LocalesModel>

    @POST("/login")
    suspend fun login(
        @Body request: LoginModel
    ): Response<String>
}