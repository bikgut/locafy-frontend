package com.example.locafyapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PerfilScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MI PERFIL", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        
        Spacer(modifier = Modifier.height(32.dp))

        // FOTO DE PERFIL
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de Perfil",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre de Usuario", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("usuario@locafy.com", color = Color.Gray)

        Spacer(modifier = Modifier.weight(1f))

        // BOTON CERRAR SESION
        Button(
            onClick = { /* Lógica de Logout */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cerrar Sesión")
        }
    }
}
