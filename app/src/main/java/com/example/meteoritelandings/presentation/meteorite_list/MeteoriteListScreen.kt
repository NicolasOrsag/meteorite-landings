package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteList
import com.example.meteoritelandings.presentation.meteorite_list.components.NavigationBottomBar
import com.example.meteoritelandings.presentation.meteorite_list.components.SearchBar
import com.example.meteoritelandings.presentation.meteorite_list.components.SortingRow

@Composable
fun MeteoriteListScreen(
    navController: NavController, viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()
    val text by viewModel.fullTextSearch.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()
    val viewFavorites by viewModel.viewFavorites.collectAsState()

    val listState = rememberLazyListState()
    var prevViewFavorites by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(viewFavorites) {
        if (viewFavorites != prevViewFavorites) {
            listState.scrollToItem(index = 0)
            prevViewFavorites = viewFavorites
        }
    }


    Scaffold(bottomBar = {
        NavigationBottomBar(
            viewFavorites = viewFavorites, setFavoriteMeteorites = viewModel::setFavoriteMeteorites
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                SearchBar(text = text, onTextChange = viewModel::setFullTextSearch)

                SortingRow(sortOption = sortOption, toggleSortOption = viewModel::toggleSortOption)

            }

            MeteoriteList(
                listState = listState,
                meteorites = meteorites,
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

}








