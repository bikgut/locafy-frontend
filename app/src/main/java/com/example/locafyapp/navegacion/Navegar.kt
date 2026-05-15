package com.example.locafyapp.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locafyapp.view.MapScreen
import com.example.locafyapp.viewModel.AdminViewModel
import com.example.locafyapp.viewModel.ClienteViewModel
import com.example.locafyapp.viewModel.DuenoViewModel
import com.example.locafyapp.viewModel.LocalesViewModel

@Composable
fun navegar(adminViewModel: AdminViewModel, clienteViewModel: ClienteViewModel, duenoViewModel: DuenoViewModel, localesViewModel: LocalesViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    )
    {
        composable("inicio"){
            MapScreen(navController)
        }
    }
}
