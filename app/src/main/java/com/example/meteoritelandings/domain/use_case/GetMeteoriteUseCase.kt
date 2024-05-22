package com.example.meteoritelandings.domain.use_case

import com.example.meteoritelandings.data.repository.MeteoriteRepository
import javax.inject.Inject

class GetMeteoriteUseCase @Inject constructor(
    private val repository: MeteoriteRepository
) {
    operator fun invoke(name: String) = repository.getMeteorite(name)
}