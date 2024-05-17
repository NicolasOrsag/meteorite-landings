package com.example.meteoritelandings.data.remote

import com.example.meteoritelandings.data.remote.dto.MeteoriteDto
import retrofit2.http.GET

interface MeteoriteApi {
    @GET("gh4g-9sfh.json")
    suspend fun getMeteoriteList(): List<MeteoriteDto>
}