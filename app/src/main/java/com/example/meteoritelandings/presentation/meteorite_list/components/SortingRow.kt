package com.example.meteoritelandings.presentation.meteorite_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.meteoritelandings.presentation.meteorite_list.SortOption
import com.example.meteoritelandings.presentation.meteorite_list.isByMass
import com.example.meteoritelandings.presentation.meteorite_list.isByName
import com.example.meteoritelandings.presentation.meteorite_list.isByYear

@Composable
fun SortingRow(sortOption: SortOption, toggleSortOption: (SortOption, SortOption) -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        SortButton(
            onClick = {
                toggleSortOption(
                    SortOption.NAME_ASC, SortOption.NAME_DESC
                )
            },
            label = "Name",
            isSelected = sortOption.isByName(),
            isDesc = sortOption == SortOption.NAME_DESC,
            modifier = Modifier.weight(1f)
        )
        SortButton(
            onClick = {
                toggleSortOption(
                    SortOption.MASS_ASC, SortOption.MASS_DESC
                )
            },
            label = "Mass",
            isSelected = sortOption.isByMass(),
            isDesc = sortOption == SortOption.MASS_DESC,
            modifier = Modifier.weight(1f)
        )
        SortButton(
            onClick = {
                toggleSortOption(
                    SortOption.YEAR_ASC, SortOption.YEAR_DESC
                )
            },
            label = "Year",
            isSelected = sortOption.isByYear(),
            isDesc = sortOption == SortOption.YEAR_DESC,
            modifier = Modifier.weight(1f)
        )
    }
}