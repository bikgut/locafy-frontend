package com.example.locafyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.locafyapp.navegacion.menu
import com.example.locafyapp.ui.theme.LocafyAppTheme
import com.example.locafyapp.view.MapScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocafyAppTheme {
                menu()
            }
        }
    }
}