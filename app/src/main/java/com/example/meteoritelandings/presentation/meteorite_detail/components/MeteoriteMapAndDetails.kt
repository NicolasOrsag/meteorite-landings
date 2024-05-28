package com.example.meteoritelandings.presentation.meteorite_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_detail.MeteoriteDetailViewModel

@Composable
fun MeteoriteMapAndDetails(
    meteorite: Meteorite,
    isFavorite: Boolean,
    viewModel: MeteoriteDetailViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        MeteoriteDetailHeader(
            name = meteorite.name,
            isFavorite = isFavorite,
            toggleFavorite = viewModel::toggleFavorite,
            onBackPressed = { navController.navigateUp() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        meteorite.reclat?.toDouble()?.let { lat ->
            meteorite.reclong?.toDouble()?.let { long ->
                MeteoriteOnMap(
                    lat = lat,
                    long = long,
                    modifier = Modifier.weight(1f).padding(16.dp)
                )
            }
        }
        MeteoriteDetails(
            meteorite = meteorite,
            modifier = Modifier.weight(1f).padding(16.dp)
        )
    }
}