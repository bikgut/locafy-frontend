package com.example.locafyapp.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*


//PARTE VISUAL DE MAPA DE GOOGLE
@Composable
fun MapScreen() {

    val santiago = LatLng(-33.45, -70.66)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(santiago, 12f)
    }

    //ESTO QUITA TODOS LOS PUNTOS DE INTERES COMO HOSPITAL, COLEGIO, CENTRO COMERCIAL, ETC
    val mapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(
            """
            [
              {
                "featureType": "poi",
                "stylers": [{ "visibility": "off" }]
              }
            ]
            """
        )
    )
    Column(modifier = Modifier.padding(20.dp).systemBarsPadding()) {
//ESTO LE DA EL TAMANO AL MAPA
        GoogleMap(
            modifier = Modifier.fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties
        ) {
            Marker(
                state = MarkerState(position = santiago),
                title = "Santiago"
            )
        }
    }
}