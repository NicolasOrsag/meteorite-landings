package com.example.meteoritelandings.presentation.meteorite_list.components

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.meteoritelandings.domain.model.Meteorite
import kotlin.math.roundToInt

@Composable
fun MeteoriteListItem(meteorite: Meteorite) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = meteorite.name, modifier = Modifier.weight(1f)
        )
        Text(
            text = "${meteorite.mass ?: "Unknown"}", modifier = Modifier.weight(0.5f)
        )
        Text(
            text = "${meteorite.year ?: "Unknown"}",
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.End
        )
    }
}