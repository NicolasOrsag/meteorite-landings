package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.data.repository.MeteoriteRepository
import com.example.meteoritelandings.presentation.meteorite_list.SortOption
import javax.inject.Inject

class GetFavoriteMeteoritesUseCase @Inject constructor(
    private val repository: MeteoriteRepository
) {
    operator fun invoke(sortOption: SortOption = SortOption.NAME_ASC) =
        repository.getFavoriteMeteorites(sortOption)
}