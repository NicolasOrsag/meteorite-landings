package com.example.meteoritelandings.presentation.meteorite_detail.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MeteoriteOnMap(lat: Double, long: Double, modifier: Modifier) {
    val meteoritePosition = LatLng(lat, long)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            meteoritePosition, 6f
        )
    }

    Card(
        shape = MaterialTheme.shapes.extraLarge, modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = meteoritePosition)
            )
        }
    }
}