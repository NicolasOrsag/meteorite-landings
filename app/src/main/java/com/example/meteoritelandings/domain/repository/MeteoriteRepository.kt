package com.example.meteoritelandings.domain.repository

import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.data.remote.dto.MeteoriteDto
import com.example.meteoritelandings.domain.model.Meteorite
import kotlinx.coroutines.flow.Flow

interface MeteoriteRepository {

    suspend fun getMeteoriteList(): Flow<Resource<List<Meteorite>>>
}