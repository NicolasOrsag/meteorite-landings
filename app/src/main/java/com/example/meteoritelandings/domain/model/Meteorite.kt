package com.example.meteoritelandings.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meteorites")
data class Meteorite(
    val fall: String,
    @PrimaryKey val id: String,
    val mass: Int?,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String?,
    val reclong: String?,
    val year: Int?
)
