package com.example.meteoritelandings.presentation.meteorite_detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.components.ErrorView
import com.example.meteoritelandings.presentation.components.LoadingView
import com.example.meteoritelandings.presentation.meteorite_detail.components.MeteoriteDetailHeader
import com.example.meteoritelandings.presentation.meteorite_detail.components.MeteoriteDetails
import com.example.meteoritelandings.presentation.meteorite_detail.components.MeteoriteOnMap


@Composable
fun MeteoriteDetailScreen(
    name: String?,
    viewModel: MeteoriteDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    val isFavorite by viewModel.isFavorite.collectAsState()

    when (val currentState = state) {
        is MeteoriteDetailScreenState.Loading -> {
            LoadingView()
        }

        is MeteoriteDetailScreenState.Loaded -> {
            val meteorite = currentState.meteorite
            meteorite?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)

                ) {
                    MeteoriteDetailHeader(name = it.name,
                        isFavorite = isFavorite,
                        toggleFavorite = viewModel::toggleFavorite,
                        onBackPressed = { navController.navigateUp() })

                    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
                    if (isLandscape) {
                        LandscapeView(meteorite = it)
                    } else {
                        PortraitView(meteorite = it)
                    }
                }
            }
        }

        is MeteoriteDetailScreenState.Error -> {
            ErrorView(errorMessage = currentState.message ?: "Unknown error",
                onRetry = { viewModel.retry() })
        }
    }
}

@Composable
private fun LandscapeView(meteorite: Meteorite) {
    Row(Modifier.fillMaxWidth()) {
        meteorite.reclat?.toDouble()?.let { lat ->
            meteorite.reclong?.toDouble()?.let { long ->
                MeteoriteOnMap(
                    lat = lat,
                    long = long,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        MeteoriteDetails(meteorite = meteorite, modifier = Modifier
            .weight(1f)
            .padding(16.dp))
    }
}

@Composable
private fun PortraitView(meteorite: Meteorite) {
    Column(Modifier.fillMaxSize()) {
        meteorite.reclat?.toDouble()?.let { lat ->
            meteorite.reclong?.toDouble()?.let { long ->
                MeteoriteOnMap(
                    lat = lat,
                    long = long,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        MeteoriteDetails(meteorite = meteorite, modifier = Modifier
            .weight(1f)
            .padding(16.dp))
    }
}







