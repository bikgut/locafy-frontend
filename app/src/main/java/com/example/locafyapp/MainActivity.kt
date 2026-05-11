package com.example.locafyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.locafyapp.navegacion.Navegar
import com.example.locafyapp.ui.theme.LocafyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocafyAppTheme {
                Navegar()
            }
        }
    }
}
