package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteoritelandings.presentation.components.ErrorView
import com.example.meteoritelandings.presentation.components.LoadingView

@Composable
fun MeteoriteDetailScreen(name: String?, viewModel: MeteoriteDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is MeteoriteDetailScreenState.Loading -> {
            LoadingView()
        }

        is MeteoriteDetailScreenState.Loaded -> {
            state.meteorite?.let {
                Column {
                    Text(text = it.name )
                    Text(text = it.mass.toString())
                    Text(text = it.year.toString())
                    Text(text = it.fall)
                    Text(text = it.recclass)
                    Text(text = it.reclat.toString())
                    Text(text = it.reclong.toString())
                }
            }
        }

        is MeteoriteDetailScreenState.Error -> {
            ErrorView(
                errorMessage = state.message ?: "Unknown error",
                onRetry = { /*TODO*/ })
        }
    }
}