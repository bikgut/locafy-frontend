package com.example.locafyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locafyapp.models.LocalesModel
import com.example.locafyapp.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocalesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    private val _categoriaSeleccionada = MutableStateFlow("Todo")
    val categoriaSeleccionada: StateFlow<String> = _categoriaSeleccionada

    private var listaCompleta = listOf<LocalesModel>()
    private var lastQuery = ""

    init {
        cargarLocales()
    }

    private fun cargarLocales() {
        viewModelScope.launch {
            listaCompleta = listOf(
                LocalesModel(1, "Pizza Nostra", "Pizza", 4.5, true, -33.458, -70.662),
                LocalesModel(2, "Sushi King", "Sushi", 4.8, false, -33.450, -70.665),
                LocalesModel(3, "Burger House", "Burgers", 4.2, false, -33.445, -70.660),
                LocalesModel(4, "Taco Express", "Mexicana", 4.0, false, -33.455, -70.670),
                LocalesModel(5, "Green Salad", "Vegana", 4.6, true, -33.460, -70.655),
                LocalesModel(6, "Pizza Hut", "Pizza", 4.1, false, -33.452, -70.650),
                LocalesModel(7, "Sushi Roll", "Sushi", 4.4, true, -33.448, -70.675)
            )
            aplicarFiltros()
        }
    }

    fun filtrarBusqueda(query: String) {
        lastQuery = query
        aplicarFiltros()
    }

    fun filtrarPorCategoria(categoria: String) {
        _categoriaSeleccionada.value = categoria
        aplicarFiltros()
    }

    private fun aplicarFiltros() {
        var filtrados = listaCompleta

        if (_categoriaSeleccionada.value != "Todo") {
            filtrados = filtrados.filter { it.tipoComida.equals(_categoriaSeleccionada.value, ignoreCase = true) }
        }

        if (lastQuery.isNotEmpty()) {
            filtrados = filtrados.filter { 
                it.nombre.contains(lastQuery, ignoreCase = true) || 
                it.tipoComida.contains(lastQuery, ignoreCase = true) 
            }
        }
        
        _uiState.value = UIState.Success(filtrados)
    }

    fun toggleFavorito(id: Int) {
        listaCompleta = listaCompleta.map { local ->
            if (local.id == id) local.copy(esFavorito = !local.esFavorito) else local
        }
        aplicarFiltros()
    }
}
