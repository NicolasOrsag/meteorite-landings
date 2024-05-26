package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.domain.use_case.AddFavoriteMeteoriteUseCase
import com.example.meteoritelandings.domain.use_case.DeleteFavoriteMeteoriteUseCase
import com.example.meteoritelandings.domain.use_case.GetFavoriteMeteoritesUseCase
import com.example.meteoritelandings.domain.use_case.GetMeteoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MeteoriteDetailViewModel @Inject constructor(
    private val getMeteoriteUseCase: GetMeteoriteUseCase,
    private val getFavoriteMeteoritesUseCase: GetFavoriteMeteoritesUseCase,
    private val addFavoriteMeteoriteUseCase: AddFavoriteMeteoriteUseCase,
    private val deleteFavoriteMeteoriteUseCase: DeleteFavoriteMeteoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<MeteoriteDetailScreenState> =
        MutableStateFlow(MeteoriteDetailScreenState.Loading())
    val state: StateFlow<MeteoriteDetailScreenState> = _state

    private val favoriteMeteorites: Flow<List<Meteorite>> = getFavoriteMeteoritesUseCase()

    private val _name: MutableStateFlow<String?> = MutableStateFlow(null)
    val name: StateFlow<String?> = _name

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        savedStateHandle.get<String>("name")?.let {
            _name.value = it
            getMeteorite(it)
        }
        viewModelScope.launch {
            combine(name, favoriteMeteorites) { meteoriteName, favoriteList ->
                favoriteList.any { it.name == meteoriteName }
            }.collect {
                _isFavorite.value = it
            }
        }
    }

    private fun getMeteorite(name: String) {
        viewModelScope.launch {
            getMeteoriteUseCase(name).collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = MeteoriteDetailScreenState.Loaded(
                            meteorite = resource.data
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = MeteoriteDetailScreenState.Loading()
                    }

                    is Resource.Error -> {
                        _state.value = MeteoriteDetailScreenState.Error(
                            resource.message ?: "An unexpected error occured"
                        )
                    }
                }

            }
        }
    }

    fun toggleFavorite() {
        if (_isFavorite.value) {
            viewModelScope.launch {
                _state.value.meteorite?.let { deleteFavoriteMeteoriteUseCase(it) }
            }
        } else {
            viewModelScope.launch {
                _state.value.meteorite?.let { addFavoriteMeteoriteUseCase(it) }
            }
        }
    }

    fun retry() {
        name.value?.let {
            getMeteorite(it)
        }
    }
}