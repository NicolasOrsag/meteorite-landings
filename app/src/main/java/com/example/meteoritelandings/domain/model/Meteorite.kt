package com.example.meteoritelandings.domain.model

import com.example.meteoritelandings.data.remote.dto.Geolocation
import java.time.LocalDateTime

data class Meteorite(
    val fall: String,
    val id: String,
    val mass: String?,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String?,
    val reclong: String?,
    val year: String?
)
