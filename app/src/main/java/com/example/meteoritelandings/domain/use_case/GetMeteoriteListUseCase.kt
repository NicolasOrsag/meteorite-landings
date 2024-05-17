package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.data.remote.dto.toMeteorite
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.domain.repository.MeteoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMeteoriteListUseCase @Inject constructor(
    private val repository: MeteoriteRepository
){
    suspend operator fun invoke()= repository.getMeteoriteList()
}