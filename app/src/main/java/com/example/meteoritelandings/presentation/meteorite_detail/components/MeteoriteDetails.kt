package com.example.meteoritelandings.presentation.meteorite_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meteoritelandings.domain.model.Meteorite

@Composable
fun MeteoriteDetails(meteorite: Meteorite, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Details",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            MeteoriteInfo("Mass", meteorite.mass?.let { "${it}g" } ?: "Unknown")
            MeteoriteInfo("Year", meteorite.year.toString())
            MeteoriteInfo("Fall", meteorite.fall)
            MeteoriteInfo("Class", meteorite.recclass)
        }


    }
}