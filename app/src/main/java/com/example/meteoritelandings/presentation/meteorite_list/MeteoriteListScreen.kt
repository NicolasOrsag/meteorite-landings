package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListContent
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListTopAppBar

@Composable
fun MeteoriteListScreen(
    navController: NavController,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()
    val text by viewModel.fullTextSearch.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()

    var isDropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { MeteoriteListTopAppBar() }
    ) { paddingValues ->
        MeteoriteListContent(
            meteorites = meteorites,
            text = text,
            onTextChange = { viewModel.setFullTextSearch(it) },
            sortOption = sortOption,
            onSortOptionChange = { viewModel.setSortOption(it) },
            isDropdownExpanded = isDropdownExpanded,
            onDropdownExpandChange = { isDropdownExpanded = it },
            navController = navController,
            paddingValues = paddingValues
        )
    }
}








