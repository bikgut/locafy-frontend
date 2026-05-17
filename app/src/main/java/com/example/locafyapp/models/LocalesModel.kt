package com.example.locafyapp.models

data class LocalesModel(
    val id: Int,
    val nombre: String,
    val tipoComida: String,
    val puntuacion: Double,
    val esFavorito: Boolean = false,
    val latitud: Double = -33.45,
    val longitud: Double = -70.66
)

sealed class UIState {
    object Loading : UIState()
    data class Success(val locales: List<LocalesModel>) : UIState()
    data class Error(val mensaje: String) : UIState()
}
