package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteoritelandings.presentation.components.ErrorView
import com.example.meteoritelandings.presentation.components.LoadingView
import com.example.meteoritelandings.presentation.meteorite_detail.components.DisplayMeteoriteDetails


@Composable
fun MeteoriteDetailScreen(name: String?, viewModel: MeteoriteDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is MeteoriteDetailScreenState.Loading -> {
            LoadingView()
        }

        is MeteoriteDetailScreenState.Loaded -> {
            val meteorite = currentState.meteorite
            meteorite?.let {
                DisplayMeteoriteDetails(meteorite = it)
            }
        }

        is MeteoriteDetailScreenState.Error -> {
            ErrorView(
                errorMessage = currentState.message ?: "Unknown error",
                onRetry = { viewModel.retry() }
            )
        }
    }
}







