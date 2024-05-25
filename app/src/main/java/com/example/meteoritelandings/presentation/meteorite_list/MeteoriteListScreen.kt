package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListContent
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListItem
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListTopAppBar
import com.example.meteoritelandings.presentation.navigation.Screen
import com.example.meteoritelandings.presentation.util.handleLoadState

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








