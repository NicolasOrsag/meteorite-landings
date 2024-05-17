package com.example.meteoritelandings.data.remote.dto

import android.util.Log
import com.example.meteoritelandings.domain.model.Meteorite
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

fun MeteoriteDto.toMeteorite(): Meteorite{
    return Meteorite(
        id = id,
        mass = mass,
        fall = fall,
        name = name,
        nametype = nametype,
        recclass = recclass,
        reclat = reclat,
        reclong = reclong,
        year = year
    )
}