package com.example.locafyapp.alertas

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun mostrarAlerta(
    titulo: String,
    mensaje: String,
    textoBtnConfirmar:String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        title = { Text(titulo)},
        text ={Text(mensaje)},
        onDismissRequest =onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = textoBtnConfirmar)
            }
        }
    )
}