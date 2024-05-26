package com.example.meteoritelandings.data.local.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meteoritelandings.domain.model.Meteorite

@Database(entities = [Meteorite::class], version = 1)
abstract class MeteoriteDatabase : RoomDatabase() {
    abstract fun favoriteMeteoriteDao(): FavoriteMeteoriteDao
}