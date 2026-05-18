package com.example.locafyapp.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locafyapp.view.*
import com.example.locafyapp.viewModel.LocalesViewModel

@Composable
fun menu(){
    val bottomNavController = rememberNavController()
    val localesViewModel: LocalesViewModel = viewModel()

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
                MapScreen().map(viewModel = localesViewModel)
            }

            composable("locales"){
                LocalesScreen(viewModel = localesViewModel)
            }

            composable("favoritos"){
                FavoritosScreen(viewModel = localesViewModel)
            }

            composable("perfil"){
                PerfilScreen(navController = bottomNavController)
            }
        }
    }
}
