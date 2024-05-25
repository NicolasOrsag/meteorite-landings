package com.example.meteoritelandings.presentation.meteorite_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.domain.use_case.GetMeteoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MeteoriteDetailViewModel @Inject constructor(
    private val getMeteoriteUseCase: GetMeteoriteUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<MeteoriteDetailScreenState> =
        MutableStateFlow(MeteoriteDetailScreenState.Loading())
    val state: StateFlow<MeteoriteDetailScreenState> = _state

    private var name: String? = null

    init {
        savedStateHandle.get<String>("name")?.let {
            name = it
            getMeteorite(it)
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

    fun retry() {
        name?.let {
            getMeteorite(it)
        }
    }
}