package com.example.meteoritelandings.presentation.meteorite_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meteoritelandings.domain.model.Meteorite

@Composable
fun MeteoriteDetails(meteorite: Meteorite) {
    Column {
        Text(
            text = "Details",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        MeteoriteInfo("Mass",
            meteorite.mass?.let { "${it}g" } ?: "Unknown",
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        MeteoriteInfo(
            "Year", meteorite.year.toString(), modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        MeteoriteInfo(
            "Fall", meteorite.fall, modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        MeteoriteInfo(
            "Class", meteorite.recclass, modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}