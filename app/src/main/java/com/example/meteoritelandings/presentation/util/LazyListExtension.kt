package com.example.meteoritelandings.presentation.util

import androidx.compose.foundation.lazy.LazyListScope
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_list.components.ErrorView
import com.example.meteoritelandings.presentation.meteorite_list.components.LoadingView

fun LazyListScope.handleLoadState(state: LoadState, meteorites: LazyPagingItems<Meteorite>) {
    when (state) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            item{
                LoadingView()
            }
        }
        is LoadState.Error -> {
            item{
                ErrorView(state.error.message ?: "Unknown error", {meteorites.retry()})
            }
        }
    }
}