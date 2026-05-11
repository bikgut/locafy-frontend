package com.example.locafyapp.models

data class LocalesModel(
    val id: Int,
    val nombre: String,
    val tipoComida: String,
    val puntuacion: Double,
    val imagenUrl: String? = null,
    val esFavorito: Boolean = false
)

// Estado de la UI para manejar Carga, Éxito y Error (Muy profesional para la comisión)
sealed class UIState {
    object Loading : UIState()
    data class Success(val locales: List<LocalesModel>) : UIState()
    data class Error(val mensaje: String) : UIState()
}