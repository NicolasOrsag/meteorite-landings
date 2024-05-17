package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MeteoriteListScreen(
    navController: NavController,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state.meteorites){
            Text(text = it.name ?: "aaa")
        }
    }
}