package com.example.meteoritelandings.presentation.meteorite_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_list.SortOption

@Composable
fun MeteoriteListContent(
    meteorites: LazyPagingItems<Meteorite>,
    text: String,
    onTextChange: (String) -> Unit,
    sortOption: SortOption,
    onSortOptionChange: (SortOption) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownExpandChange: (Boolean) -> Unit,
    navController: NavController,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        SearchAndSortRow(
            text = text,
            onTextChange = onTextChange,
            sortOption = sortOption,
            onSortOptionChange = onSortOptionChange,
            isDropdownExpanded = isDropdownExpanded,
            onDropdownExpandChange = onDropdownExpandChange
        )

        MeteoriteList(
            meteorites = meteorites,
            navController = navController
        )
    }
}