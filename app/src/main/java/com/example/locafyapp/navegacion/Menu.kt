package com.example.locafyapp.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.locafyapp.view.MapScreen
import androidx.navigation.compose.composable
import com.example.locafyapp.view.MenuInicio


@Composable
fun menu(){
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            MenuInicio(bottomNavController)
        }
    ) { innerPadding ->

        NavHost(
            navController = bottomNavController,
            startDestination = "map",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("map"){
                MapScreen().map()
            }

            composable("locales"){
                //LocalesScreen()
            }

            composable("favoritos"){
                //FavoritoScreen()
            }

            composable("perfil"){
                //PerfilScreen()
            }
        }
    }
}