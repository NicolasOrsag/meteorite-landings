package com.example.meteoritelandings.data.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.data.remote.dto.toMeteorite
import com.example.meteoritelandings.data.repository.MeteoriteRepositoryImpl
import com.example.meteoritelandings.domain.model.Meteorite
import javax.inject.Inject

class MeteoritePagingSource(
    private val api: MeteoriteApi,
    private val fullTextSearch: String
) : PagingSource<Int, Meteorite>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meteorite> {
        return try {
            val page = params.key ?: 0
            val offset = page * params.loadSize
            val meteorites = api.getMeteoriteList( offset, params.loadSize, fullTextSearch)
            LoadResult.Page(
                data = meteorites.map { it.toMeteorite() },
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (meteorites.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meteorite>): Int? {
        return state.anchorPosition
    }
}