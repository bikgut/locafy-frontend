package com.example.locafyapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.locafyapp.models.UIState
import com.example.locafyapp.viewModel.LocalesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

class MapScreen(private val navcontroller : NavHostController? = null) {

    @Composable
    fun map(viewModel: LocalesViewModel) {
        val state by viewModel.uiState.collectAsState()
        val santiago = LatLng(-33.45, -70.66)

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(santiago, 12f)
        }

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
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings =  mapUiSettings,
                contentPadding = PaddingValues(bottom = 80.dp, top = 80.dp)
            ) {
                // MOSTRAR TODOS LOS LOCALES REGISTRADOS EN LA APP
                if (state is UIState.Success) {
                    (state as UIState.Success).locales.forEach { local ->
                        Marker(
                            state = MarkerState(position = LatLng(local.latitud, local.longitud)),
                            title = local.nombre,
                            snippet = local.tipoComida
                        )
                    }
                }
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
