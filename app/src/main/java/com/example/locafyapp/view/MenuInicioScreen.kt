package com.example.locafyapp.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.material.icons.filled.*

@Composable
fun MenuInicio(navController: NavController){

    NavigationBar{
        //MAPA
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("map")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Mapa"
                )
            }
        )
        //LOCALES
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("locales")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.RestaurantMenu,
                    contentDescription = "locales"
                )
            }
        )

        //FAVORITOS
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("favoritos")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favoritos"
                )
            }
        )

        //PERFIL
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("perfil")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "perfil"
                )
            }
        )
    }
}