package com.example.meteoritelandings.presentation.meteorite_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meteoritelandings.domain.model.Meteorite

@Composable
fun DisplayMeteoriteDetails(meteorite: Meteorite) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = meteorite.name ?: "",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        DisplayMeteoriteInfo("Mass", meteorite.mass.toString())
        DisplayMeteoriteInfo("Year", meteorite.year.toString())
        DisplayMeteoriteInfo("Fall", meteorite.fall)
        DisplayMeteoriteInfo("Class", meteorite.recclass)

        Spacer(modifier = Modifier.height(16.dp))

        meteorite.reclat?.toDouble()?.let { lat ->
            meteorite.reclong?.toDouble()?.let { long ->
                DisplayMeteoriteOnMap(lat = lat, long = long)
            }
        }
    }
}