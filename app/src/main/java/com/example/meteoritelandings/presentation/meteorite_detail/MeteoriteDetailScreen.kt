package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
                    MeteoriteDetailHeader(name = it.name) { navController.navigateUp() }

                    Spacer(modifier = Modifier.height(16.dp))

                    it.reclat?.toDouble()?.let { lat ->
                        it.reclong?.toDouble()?.let { long ->
                            MeteoriteOnMap(
                                lat = lat,
                                long = long,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                    MeteoriteDetails(meteorite = meteorite)
                }
            }
        }

        is MeteoriteDetailScreenState.Error -> {
            ErrorView(errorMessage = currentState.message ?: "Unknown error",
                onRetry = { viewModel.retry() })
        }
    }
}







