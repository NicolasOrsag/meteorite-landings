package com.example.meteoritelandings.presentation.meteorite_detail

import com.example.meteoritelandings.domain.model.Meteorite

sealed class MeteoriteDetailScreenState(val meteorite: Meteorite? = null, val message: String? = null) {
    class Loading : MeteoriteDetailScreenState()
    class Loaded(meteorite: Meteorite?) : MeteoriteDetailScreenState(meteorite = meteorite)
    class Error(message: String) : MeteoriteDetailScreenState(message = message)
}