package com.example.meteoritelandings.data.local.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meteoritelandings.domain.model.Meteorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMeteoriteDao {
    @Query("SELECT * FROM favorite_meteorites ORDER BY name ASC")
    fun getFavoriteMeteoritesByNameAsc(): Flow<List<Meteorite>>

    @Query("SELECT * FROM favorite_meteorites ORDER BY name DESC")
    fun getFavoriteMeteoritesByNameDesc(): Flow<List<Meteorite>>

    @Query("SELECT * FROM favorite_meteorites ORDER BY mass ASC")
    fun getFavoriteMeteoritesByMassAsc(): Flow<List<Meteorite>>

    @Query("SELECT * FROM favorite_meteorites ORDER BY mass DESC")
    fun getFavoriteMeteoritesByMassDesc(): Flow<List<Meteorite>>

    @Query("SELECT * FROM favorite_meteorites ORDER BY year ASC")
    fun getFavoriteMeteoritesByYearAsc(): Flow<List<Meteorite>>

    @Query("SELECT * FROM favorite_meteorites ORDER BY year DESC")
    fun getFavoriteMeteoritesByYearDesc(): Flow<List<Meteorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeteorite(meteorite: Meteorite)

    @Query("DELETE FROM favorite_meteorites WHERE id = :id")
    suspend fun deleteFavoriteMeteoriteById(id: String)
}