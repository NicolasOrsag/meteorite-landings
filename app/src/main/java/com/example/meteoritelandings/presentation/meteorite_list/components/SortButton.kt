package com.example.meteoritelandings.presentation.meteorite_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortButton(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    isDesc: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(vertical = 16.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.extraLarge
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(vertical = 8.dp)) {
            Text(
                text = label,
                color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = if (isDesc) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                tint = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }

}