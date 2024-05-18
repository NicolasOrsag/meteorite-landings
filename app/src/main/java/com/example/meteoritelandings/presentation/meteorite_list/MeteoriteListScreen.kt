package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListItem
import com.example.meteoritelandings.presentation.util.handleLoadState

@Composable
fun MeteoriteListScreen(
    navController: NavController,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    //val state by viewModel.state.collectAsState()

    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()

    var text = viewModel.fullTextSearch.collectAsState()

    Column {
        TextField(value = text.value , onValueChange = { viewModel.setFullTextSearch(it) })

        LazyColumn {
            handleLoadState(meteorites.loadState.refresh, meteorites)
            handleLoadState(meteorites.loadState.prepend, meteorites)
            items(meteorites.itemCount){index ->
                meteorites[index]?.let{
                    MeteoriteListItem(meteorite = it)
                }
            }
            handleLoadState(meteorites.loadState.append, meteorites)
        }
    }

}
