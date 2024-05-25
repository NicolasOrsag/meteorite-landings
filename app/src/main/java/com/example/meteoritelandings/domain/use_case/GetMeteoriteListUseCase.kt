package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.data.repository.MeteoriteRepository
import javax.inject.Inject

class GetMeteoriteListUseCase @Inject constructor(
    private val repository: MeteoriteRepository
) {
    operator fun invoke(fullTextSearch: String, order: String) =
        repository.getMeteoriteList(fullTextSearch, order)
}