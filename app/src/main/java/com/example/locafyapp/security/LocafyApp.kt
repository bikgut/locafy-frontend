package com.example.locafyapp.security

import android.app.Application

class LocafyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TokenManager.inicializar(this)
    }
}