package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.data.repository.MeteoriteRepository
import javax.inject.Inject

class GetFavoriteMeteoritesUseCase @Inject constructor(
    private val repository: MeteoriteRepository
) {
    operator fun invoke() =
        repository.getFavoriteMeteorites()
}