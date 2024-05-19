package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListItem
import com.example.meteoritelandings.presentation.util.handleLoadState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoriteListScreen(
    navController: NavController,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()
    val text = viewModel.fullTextSearch.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meteorite Landings") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { viewModel.setFullTextSearch(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    placeholder = { Text("Search meteorites...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                    )
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    handleLoadState(meteorites.loadState.refresh, meteorites)
                    handleLoadState(meteorites.loadState.prepend, meteorites)
                    items(meteorites.itemCount) { index ->
                        meteorites[index]?.let { meteorite ->
                            MeteoriteListItem(meteorite = meteorite)
                            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                        }
                    }
                    handleLoadState(meteorites.loadState.append, meteorites)
                }
            }
        }
    )
}

