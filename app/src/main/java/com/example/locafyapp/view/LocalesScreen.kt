package com.example.locafyapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.locafyapp.models.LocalesModel
import com.example.locafyapp.models.UIState
import com.example.locafyapp.viewModel.LocalesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalesScreen(viewModel: LocalesViewModel, navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    var searchTexto by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Locafy - Comida Local", fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            // 1. BARRA DE BÚSQUEDA
            OutlinedTextField(
                value = searchTexto,
                onValueChange = {
                    searchTexto = it
                    viewModel.filtrarBusqueda(it)
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = { Text("¿Qué se te antoja buscar?") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(24.dp),
                singleLine = true
            )

            // 2. FILTROS RÁPIDOS
            val categorias = listOf("Todo", "Sushi", "Pizza", "Burgers", "Vegano")
            val catActual by viewModel.categoriaSeleccionada.collectAsState()
            
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categorias) { cat ->
                    FilterChip(
                        selected = catActual == cat,
                        onClick = { viewModel.filtrarPorCategoria(cat) },
                        label = { Text(cat) }
                    )
                }
            }

            // 3. ESTADO DE CARGA Y LISTA
            when (val uiState = state) {
                is UIState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                is UIState.Success -> {
                    if (uiState.locales.isEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No se encontraron locales", color = Color.Gray)
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.locales, key = { it.id }) { local ->
                                LocalCard(local) { viewModel.toggleFavorito(local.id) }
                            }
                        }
                    }
                }
                is UIState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${uiState.mensaje}", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun LocalCard(local: LocalesModel, onFavClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            // Imagen Placeholder (puedes usar AsyncImage de Coil aquí)
            Box(modifier = Modifier.size(65.dp).clip(RoundedCornerShape(12.dp)).background(Color.Gray))

            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(local.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(local.tipoComida, style = MaterialTheme.typography.bodySmall)

                // PUNTUACIÓN ESTRELLAS
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (index < local.puntuacion.toInt()) Color(0xFFFFB400) else Color.LightGray
                        )
                    }
                    Text(" (${local.puntuacion})", fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                }
            }

            IconButton(onClick = onFavClick) {
                Icon(
                    imageVector = if (local.esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (local.esFavorito) Color.Red else Color.Gray
                )
            }
        }
    }
}