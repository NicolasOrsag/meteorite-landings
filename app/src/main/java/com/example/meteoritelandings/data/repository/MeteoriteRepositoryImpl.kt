package com.example.meteoritelandings.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.meteoritelandings.common.Constants.PAGE_SIZE
import com.example.meteoritelandings.data.pagination.MeteoritePagingSource
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.domain.model.Meteorite
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class MeteoriteRepositoryImpl @Inject constructor(
    private val api: MeteoriteApi
) : MeteoriteRepository {


    override fun getMeteoriteList(fullTextSearch: String): Flow<PagingData<Meteorite>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {MeteoritePagingSource(api, fullTextSearch)}
        ).flow
    }

}