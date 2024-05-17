package com.example.meteoritelandings.presentation.meteorite_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meteoritelandings.common.Resource
import com.example.meteoritelandings.domain.use_case.GetMeteoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeteoriteListViewModel @Inject constructor(
    private val getMeteoriteListUseCase: GetMeteoriteListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MeteoriteListState())
    val state: StateFlow<MeteoriteListState> get()= _state

    init {
        getMeteorites()
    }


    private fun getMeteorites() {
        viewModelScope.launch {
            getMeteoriteListUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = MeteoriteListState(meteorites = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = MeteoriteListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = MeteoriteListState(isLoading = true)
                    }
                }
            }
        }
    }

}