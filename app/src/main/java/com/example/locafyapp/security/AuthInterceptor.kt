package com.example.locafyapp.security

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response{
        val requestOriginal = chain.request()

        val token = TokenManager.obtenerToken()

        val requestModificado = if(token != null){

            Log.d("PruebaToken", "Viaja el token: $token")

            requestOriginal.newBuilder().addHeader("Autorizacion", "Bearer $token").build()
        }else{
            Log.e("PruebaToken", "El token es vacio o nulo")

            requestOriginal
        }

        return chain.proceed(requestModificado)
    }

}