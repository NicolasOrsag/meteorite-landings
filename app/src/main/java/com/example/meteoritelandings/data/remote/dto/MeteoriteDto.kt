package com.example.meteoritelandings.data.remote.dto

import com.example.meteoritelandings.domain.model.Meteorite
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

data class MeteoriteDto(
    val fall: String,
    val geolocation: Geolocation,
    val id: String,
    val mass: String,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String,
    val reclong: String,
    val year: String
)

fun MeteoriteDto.toMeteorite(): Meteorite {
    val dateTime = year?.let {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        LocalDateTime.parse(it, formatter)
    }

    return Meteorite(
        id = id,
        mass = mass?.toDouble()?.roundToInt(),
        fall = fall,
        name = name,
        nametype = nametype,
        recclass = recclass,
        reclat = reclat,
        reclong = reclong,
        year = dateTime?.year
    )
}