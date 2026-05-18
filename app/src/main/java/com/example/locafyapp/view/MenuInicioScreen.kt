package com.example.locafyapp.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MenuInicio(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        //MAPA
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "map" } == true,
            onClick = {
                navController.navigate("map") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Mapa") },
            label = { Text("Mapa") }
        )
        //LOCALES
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "locales" } == true,
            onClick = {
                navController.navigate("locales") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.RestaurantMenu, contentDescription = "locales") },
            label = { Text("Locales") }
        )

        //FAVORITOS
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "favoritos" } == true,
            onClick = {
                navController.navigate("favoritos") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "favoritos") },
            label = { Text("Favoritos") }
        )

        //PERFIL
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "perfil" } == true,
            onClick = {
                navController.navigate("perfil") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "perfil") },
            label = { Text("Perfil") }
        )
    }
}
