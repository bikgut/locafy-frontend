package com.example.locafyapp.security

import android.content.Context
import android.content.SharedPreferences

object TokenManager {

    private const val PREFS_NAME = "locafy_seguridad"

    private const val TOKEN_KEY ="jwt_token"

    private var prefs: SharedPreferences? = null

    fun inicializar(context: Context){
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun guardarToken(token: String){
        prefs?.edit()?.putString(TOKEN_KEY, token)?.commit()
    }

    fun obtenerToken(): String? {
        return prefs?.getString(TOKEN_KEY, null)
    }

    fun borrarToken(){
        prefs?.edit()?.remove(TOKEN_KEY)?.apply()
    }
}