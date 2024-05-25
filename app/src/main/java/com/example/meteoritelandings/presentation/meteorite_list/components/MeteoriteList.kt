package com.example.meteoritelandings.presentation.meteorite_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.navigation.Screen
import com.example.meteoritelandings.presentation.util.handleLoadState

@Composable
fun MeteoriteList(
    meteorites: LazyPagingItems<Meteorite>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        handleLoadState(meteorites.loadState.refresh, meteorites)
        handleLoadState(meteorites.loadState.prepend, meteorites)
        items(meteorites.itemCount) { index ->
            meteorites[index]?.let { meteorite ->
                MeteoriteListItem(
                    meteorite = meteorite,
                    onMeteoriteClick = {
                        navController.navigate(Screen.MeteoriteDetail.withArgs(it))
                    }
                )
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
            }
        }
        handleLoadState(meteorites.loadState.append, meteorites)
    }
}