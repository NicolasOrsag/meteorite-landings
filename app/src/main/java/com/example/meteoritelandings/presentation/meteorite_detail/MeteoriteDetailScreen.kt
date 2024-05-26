package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meteoritelandings.presentation.components.ErrorView
import com.example.meteoritelandings.presentation.components.LoadingView
import com.example.meteoritelandings.presentation.meteorite_detail.components.MeteoriteDetailHeader
import com.example.meteoritelandings.presentation.meteorite_detail.components.MeteoriteInfo
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
                    Text(
                        text = "Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp)
                    )

                    MeteoriteInfo("Mass",
                        meteorite.mass?.let { "${it}g" } ?: "Unknown",
                        modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    MeteoriteInfo(
                        "Year",
                        meteorite.year.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp)
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
        }

        is MeteoriteDetailScreenState.Error -> {
            ErrorView(errorMessage = currentState.message ?: "Unknown error",
                onRetry = { viewModel.retry() })
        }
    }
}







