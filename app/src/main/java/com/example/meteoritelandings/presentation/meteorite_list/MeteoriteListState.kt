package com.example.meteoritelandings.presentation.meteorite_list

import com.example.meteoritelandings.domain.model.Meteorite

data class MeteoriteListState(
    val fulltextSearch: String = "",
    val isLoading: Boolean = false,
    val meteorites: List<Meteorite> = emptyList(),
    val error: String = ""
)
