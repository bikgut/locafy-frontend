package com.example.locafyapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locafyapp.models.LoginModel
import com.example.locafyapp.repository.LocalService
import com.example.locafyapp.security.TokenManager
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var loginViewModel by mutableStateOf(LoginModel("", ""))
        private set

    fun cambioUsername(nuevoUsername:String){
        loginViewModel = loginViewModel.copy(username = nuevoUsername)
    }

    fun cambioPassword(nuevoPassword:String){
        loginViewModel = loginViewModel.copy(password = nuevoPassword)
    }

    //ALERTAS

    var verAlerta by mutableStateOf(false)
        private set

    var tituloAlerta by mutableStateOf("")
        private set

    var mensajeAlerta by mutableStateOf("")
        private set

    var textoBtnAlerta by mutableStateOf("")
        private set

    fun descartarAlerta(){
        verAlerta = false
    }

    //NAVEGACION

    var navegan by mutableStateOf(false)
        private set

    fun cambiarNavegan(){
        navegan = false
    }

    fun hacerLogin(){
        viewModelScope.launch {
            try {
                val request = LoginModel(
                    username = loginViewModel.username,
                    password = loginViewModel.password
                )

                val response = LocalService.instance.login(request)

                if(response.isSuccessful){
                    val token = response.body()

                    if(token != null){
                        TokenManager.guardarToken(token)
                        Log.d("SEGURIDAD_APP", "Token guardado en el celular exitosamente")

                        navegan = true
                    }
                }else{
                    tituloAlerta = "Acceso denegado"
                    mensajeAlerta = "Usuario o clave incorrectos."
                    textoBtnAlerta = "Reintentar"
                    verAlerta = true
                }
            }catch(e: Exception){

                e.printStackTrace()
                tituloAlerta = "Error de conexion"
                mensajeAlerta = "No se pudo conectar con el servidor."
                textoBtnAlerta = "Entendido"
                verAlerta = true
            }
        }
    }




}