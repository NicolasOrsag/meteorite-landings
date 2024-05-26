package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteList
import com.example.meteoritelandings.presentation.meteorite_list.components.SearchBar
import com.example.meteoritelandings.presentation.meteorite_list.components.SortingRow

@Composable
fun MeteoriteListScreen(
    navController: NavController, viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()
    val text by viewModel.fullTextSearch.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
        ) {
            SearchBar(text = text, onTextChange = viewModel::setFullTextSearch)

            SortingRow(sortOption = sortOption, toggleSortOption = viewModel::toggleSortOption)

            Button(onClick = {viewModel.setFavoriteMeteorites()}) {
                Text("Favorites")

            }
        }

        MeteoriteList(
            meteorites = meteorites,
            navController = navController,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}








