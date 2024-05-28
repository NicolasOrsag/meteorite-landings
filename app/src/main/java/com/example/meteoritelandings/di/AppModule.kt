package com.example.meteoritelandings.di

import android.content.Context
import androidx.room.Room
import com.example.meteoritelandings.data.local.databse.FavoriteMeteoriteDao
import com.example.meteoritelandings.data.local.databse.MeteoriteDatabase
import com.example.meteoritelandings.data.remote.AppTokenInterceptor
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.data.repository.MeteoriteRepository
import com.example.meteoritelandings.data.repository.MeteoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://data.nasa.gov/resource/"
    private const val APP_TOKEN = "kX3CpVEPwKoL7dzxj0CNed5yP"

    @Singleton
    @Provides
    fun provideMeteoriteRepository(
        api: MeteoriteApi, favoriteMeteoriteDao: FavoriteMeteoriteDao
    ): MeteoriteRepository = MeteoriteRepositoryImpl(api, favoriteMeteoriteDao)

    @Singleton
    @Provides
    fun provideMeteoriteApi(): MeteoriteApi {
        val interceptor = AppTokenInterceptor(APP_TOKEN)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client)
                .baseUrl(BASE_URL).build()
        return retrofit.create(MeteoriteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMeteoriteDatabase(@ApplicationContext context: Context): MeteoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext, MeteoriteDatabase::class.java, "meteorite_database"
        ).build()
    }

    @Provides
    fun provideFavoriteMeteoriteDao(database: MeteoriteDatabase): FavoriteMeteoriteDao {
        return database.favoriteMeteoriteDao()
    }
}