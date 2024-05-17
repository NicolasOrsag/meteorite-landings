package com.example.meteoritelandings.data.repository

import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.data.remote.dto.toMeteorite
import com.example.meteoritelandings.domain.repository.MeteoriteRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityScoped
class MeteoriteRepositoryImpl @Inject constructor(
    private val api: MeteoriteApi
) : MeteoriteRepository {

    override suspend fun getMeteoriteList() =
        flow {
                emit(Resource.Loading())
                val meteoriteList = api.getMeteoriteList().map { it.toMeteorite() }
                emit(Resource.Success(meteoriteList))

        }.flowOn(Dispatchers.IO)
}