package com.example.meteoritelandings.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.data.local.databse.FavoriteMeteoriteDao
import com.example.meteoritelandings.data.pagination.MeteoritePagingSource
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.data.remote.dto.toMeteorite
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.presentation.meteorite_list.SortOption
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val PAGE_SIZE = 20

@ActivityScoped
class MeteoriteRepositoryImpl @Inject constructor(
    private val api: MeteoriteApi,
    private val favoriteMeteoriteDao: FavoriteMeteoriteDao
) : MeteoriteRepository {
    override fun getMeteoriteList(
        fullTextSearch: String,
        order: String
    ): Flow<PagingData<Meteorite>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MeteoritePagingSource(api, fullTextSearch, order) }
        ).flow
    }

    override fun getMeteorite(name: String): Flow<Resource<Meteorite>> {
        return flow {
            try {
                emit(Resource.Loading())
                val meteorite = api.getMeteorite(name).first().toMeteorite()
                emit(Resource.Success(meteorite))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addFavoriteMeteorite(meteorite: Meteorite) {
        return favoriteMeteoriteDao.insertFavoriteMeteorite(meteorite)
    }

    override suspend fun deleteFavoriteMeteorite(meteorite: Meteorite) {
        return favoriteMeteoriteDao.deleteFavoriteMeteoriteById(meteorite.id)
    }

    override fun getFavoriteMeteorites(sortOption: SortOption): Flow<List<Meteorite>> {
        return when (sortOption) {
            SortOption.NAME_ASC -> favoriteMeteoriteDao.getFavoriteMeteoritesByNameAsc()
            SortOption.NAME_DESC -> favoriteMeteoriteDao.getFavoriteMeteoritesByNameDesc()
            SortOption.MASS_ASC -> favoriteMeteoriteDao.getFavoriteMeteoritesByMassAsc()
            SortOption.MASS_DESC -> favoriteMeteoriteDao.getFavoriteMeteoritesByMassDesc()
            SortOption.YEAR_ASC -> favoriteMeteoriteDao.getFavoriteMeteoritesByYearAsc()
            SortOption.YEAR_DESC -> favoriteMeteoriteDao.getFavoriteMeteoritesByYearDesc()
        }
    }


}