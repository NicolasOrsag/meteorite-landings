package com.example.meteoritelandings.data.local.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meteorites")
data class FavoriteMeteorite(
    @PrimaryKey val id: String,
    val fall: String,
    val mass: Int?,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String?,
    val reclong: String?,
    val year: Int?
)