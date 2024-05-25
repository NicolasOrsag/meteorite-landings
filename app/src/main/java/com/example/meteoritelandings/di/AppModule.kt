package com.example.meteoritelandings.di

import com.example.meteoritelandings.common.Constants.BASE_URL
import com.example.meteoritelandings.data.remote.MeteoriteApi
import com.example.meteoritelandings.data.repository.MeteoriteRepository
import com.example.meteoritelandings.data.repository.MeteoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMeteoriteRepository(
        api: MeteoriteApi
    ): MeteoriteRepository = MeteoriteRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideMeteoriteApi(): MeteoriteApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MeteoriteApi::class.java)
    }
}