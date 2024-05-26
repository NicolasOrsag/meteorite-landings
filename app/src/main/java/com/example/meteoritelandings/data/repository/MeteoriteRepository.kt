package com.example.meteoritelandings.data.repository

import androidx.paging.PagingData
import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_list.SortOption
import kotlinx.coroutines.flow.Flow

interface MeteoriteRepository {

    fun getMeteoriteList(
        fullTextSearch: String = "",
        order: String = ""
    ): Flow<PagingData<Meteorite>>

    fun getMeteorite(name: String): Flow<Resource<Meteorite>>


    suspend fun addFavoriteMeteorite(meteorite: Meteorite)
    suspend fun deleteFavoriteMeteorite(meteorite: Meteorite)
    fun getFavoriteMeteorites(sortOption: SortOption): Flow<List<Meteorite>>

}