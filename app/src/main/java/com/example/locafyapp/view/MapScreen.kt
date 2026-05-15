package com.example.locafyapp.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*


//PARTE VISUAL DE MAPA DE GOOGLE

class MapScreen(private val navcontroller : NavHostController? = null) {

    @Composable
    fun map() {

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

        val mapUiSettings = MapUiSettings(
            zoomControlsEnabled = true,
            mapToolbarEnabled = false
        )

        Box(modifier = Modifier.fillMaxSize()) {
        //ESTO LE DA EL TAMAÑO AL MAPA
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings =  mapUiSettings,
                contentPadding = PaddingValues(bottom = 80.dp, top = 80.dp)
            ) {
                Marker(
                    state = MarkerState(position = santiago),
                    title = "Santiago"
                )
            }

            //TITULO NOMBRE APP
            Box (
                modifier =  Modifier.fillMaxWidth().align(Alignment.TopCenter).background(Color.Black.copy(alpha = 0.85f))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Locafy",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}