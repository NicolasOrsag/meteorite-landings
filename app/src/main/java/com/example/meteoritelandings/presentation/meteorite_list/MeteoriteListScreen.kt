package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListItem
import kotlinx.coroutines.flow.filter

@Composable
fun MeteoriteListScreen(
    navController: NavController,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    //val state by viewModel.state.collectAsState()

    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()

    var text = viewModel.searchText.collectAsState()


    Column {
        TextField(value = text.value , onValueChange = { viewModel.setSearchQuery(it) })

        LazyColumn {
            items(meteorites.itemCount){index ->
                meteorites[index]?.let{
                    MeteoriteListItem(meteorite = it)
                }

            }

        }
    }

}