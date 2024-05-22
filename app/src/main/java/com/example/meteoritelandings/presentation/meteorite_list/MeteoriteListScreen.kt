package com.example.meteoritelandings.presentation.meteorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meteoritelandings.presentation.meteorite_list.components.MeteoriteListItem
import com.example.meteoritelandings.presentation.navigation.Screen
import com.example.meteoritelandings.presentation.util.handleLoadState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoriteListScreen(
    navController: NavController, viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites = viewModel.meteorites.collectAsLazyPagingItems()
    val text = viewModel.fullTextSearch.collectAsState()
    val sortOption = viewModel.sortOption.collectAsState()

    var isDropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Meteorite Landings") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { viewModel.setFullTextSearch(it) },
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
                        .clickable { isDropdownExpanded = !isDropdownExpanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand/Collapse",
                        modifier = Modifier.rotate(if (isDropdownExpanded) 180f else 0f)
                    )
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        SortOption.entries.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setSortOption(option)
                                    isDropdownExpanded = false
                                }, text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = option.stringValue.replaceFirstChar { it.uppercase() },
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (option == sortOption.value) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.onSurface
                                            }
                                        )
                                        if (option == sortOption.value) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Active Sorting",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }

                                }
                            )
                        }
                    }
                }
            }




            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                handleLoadState(meteorites.loadState.refresh, meteorites)
                handleLoadState(meteorites.loadState.prepend, meteorites)
                items(meteorites.itemCount) { index ->
                    meteorites[index]?.let { meteorite ->
                        MeteoriteListItem(meteorite = meteorite, onMeteoriteClick = {
                            navController.navigate(
                                Screen.MeteoriteDetail.withArgs(it)
                            )
                        })
                        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                    }
                }
                handleLoadState(meteorites.loadState.append, meteorites)
            }
        }
    }
}

