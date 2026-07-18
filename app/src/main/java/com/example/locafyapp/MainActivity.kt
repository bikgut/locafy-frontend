package com.example.locafyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.locafyapp.navegacion.menu
import com.example.locafyapp.navegacion.navegar
import com.example.locafyapp.ui.theme.LocafyAppTheme
import com.example.locafyapp.view.MapScreen
import com.example.locafyapp.viewModel.AdminViewModel
import com.example.locafyapp.viewModel.ClienteViewModel
import com.example.locafyapp.viewModel.DuenoViewModel
import com.example.locafyapp.viewModel.LocalesViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocafyAppTheme {
                navegar()
            }
        }
    }
}