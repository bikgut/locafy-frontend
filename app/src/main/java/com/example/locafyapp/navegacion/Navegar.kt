package com.example.locafyapp.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locafyapp.view.*
import com.example.locafyapp.viewModel.LocalesViewModel

@Composable
fun Navegar() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mapa") {
        composable("mapa") {
            MapScreen(navController = navController)
        }
        composable("locales") {
            val viewModel: LocalesViewModel = viewModel()
            LocalesScreen(viewModel = viewModel, navController = navController)
        }
        composable("cliente") {
            ClienteScreen(navController = navController)
        }
    }
}
