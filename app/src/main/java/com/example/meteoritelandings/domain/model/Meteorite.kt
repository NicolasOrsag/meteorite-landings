package com.example.meteoritelandings.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Meteorite(
    val fall: String,
    val id: String,
    val mass: Int?,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String?,
    val reclong: String?,
    val year: Int?
)
