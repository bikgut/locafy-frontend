package com.example.locafyapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ClienteScreen(navController: NavController) {
    var searchFavoritos by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        
        // --- SECCIÓN PERFIL ---
        Text("PERFIL", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            // FOTO DE PERFIL
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de Perfil",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(45.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text("Nombre de Usuario", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                
                // BOTON CERRAR SESION
                Button(
                    onClick = { /* Lógica de Logout */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, modifier = Modifier.size(16.dp))
                    Text(" Cerrar Sesión", fontSize = 12.sp)
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))

        // --- SECCIÓN FAVORITOS ---
        Text("FAVORITOS", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
        
        // BARRA DE BUSQUEDA (Favoritos)
        OutlinedTextField(
            value = searchFavoritos,
            onValueChange = { searchFavoritos = it },
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            placeholder = { Text("Buscar en mis favoritos...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(24.dp),
            singleLine = true
        )

        // MOSTRAR LISTA DE LOCALES FAVORITOS
        Text("Tus locales favoritos aparecerán aquí:", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        
        Spacer(modifier = Modifier.height(8.dp))

        // Placeholder de un item en la lista con BOTON PARA ELIMINAR FAVORITO
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray))
                
                Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                    Text("Local de Ejemplo", fontWeight = FontWeight.Bold)
                    Text("Comida Rápida", fontSize = 12.sp)
                }

                // BOTÓN PARA ELIMINAR FAVORITO
                IconButton(onClick = { /* Acción para eliminar */ }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar de favoritos", tint = Color.Red)
                }
            }
        }
    }
}
