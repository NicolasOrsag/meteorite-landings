package com.example.meteoritelandings.presentation.meteorite_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.domain.use_case.GetMeteoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MeteoriteListViewModel @Inject constructor(
    private val getMeteoriteListUseCase: GetMeteoriteListUseCase,
) : ViewModel() {
    //private val _state = MutableStateFlow(MeteoriteListState())
    //val state: StateFlow<MeteoriteListState> get()= _state

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    //val meteorites: Flow<PagingData<Meteorite>> = getMeteoriteListUseCase().cachedIn(viewModelScope)

    val meteorites: Flow<PagingData<Meteorite>> = _searchText.flatMapLatest { searchText ->
        getMeteoriteListUseCase(searchText).cachedIn(viewModelScope)
    }

    fun setSearchQuery(query: String) {
        _searchText.value = query
    }


//    private fun getMeteorites() {
//        viewModelScope.launch {
//            getMeteoriteListUseCase().collectLatest { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        _state.value = MeteoriteListState(meteorites = result.data ?: emptyList())
//                    }
//
//                    is Resource.Error -> {
//                        _state.value = MeteoriteListState(
//                            error = result.message ?: "An unexpected error occured"
//                        )
//                    }
//
//                    is Resource.Loading -> {
//                        _state.value = MeteoriteListState(isLoading = true)
//                    }
//                }
//            }
//        }
//    }

}