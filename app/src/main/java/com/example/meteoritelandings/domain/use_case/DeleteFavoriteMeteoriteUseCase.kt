package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.data.repository.MeteoriteRepository
import com.example.meteoritelandings.domain.model.Meteorite
import javax.inject.Inject

class DeleteFavoriteMeteoriteUseCase @Inject constructor(
    private val repository: MeteoriteRepository
) {
    suspend operator fun invoke(meteorite: Meteorite) =
        repository.deleteFavoriteMeteorite(meteorite)
}