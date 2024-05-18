package com.example.meteoritelandings.data.remote

import com.example.meteoritelandings.data.remote.dto.MeteoriteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MeteoriteApi {
    @GET("gh4g-9sfh.json")
    suspend fun getMeteoriteList(

        @Query("\$offset") offset: Int,
        @Query("\$limit") limit: Int

    ): List<MeteoriteDto>
}