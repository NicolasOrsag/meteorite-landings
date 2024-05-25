package com.example.meteoritelandings.presentation.meteorite_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.meteoritelandings.presentation.meteorite_list.SortOption

@Composable
fun SearchAndSortRow(
    text: String,
    onTextChange: (String) -> Unit,
    sortOption: SortOption,
    onSortOptionChange: (SortOption) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownExpandChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = { Text("Search meteorites...") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary,
            )
        )

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.CenterEnd)
                .padding(start = 8.dp)
                .clickable { onDropdownExpandChange(!isDropdownExpanded) }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expand/Collapse",
                modifier = Modifier.rotate(if (isDropdownExpanded) 180f else 0f)
            )
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { onDropdownExpandChange(false) },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                SortOption.entries.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onSortOptionChange(option)
                            onDropdownExpandChange(false)
                        },
                        text = {
                            SortOptionItem(
                                option = option,
                                isSelected = option == sortOption
                            )
                        }
                    )
                }
            }
        }
    }
}