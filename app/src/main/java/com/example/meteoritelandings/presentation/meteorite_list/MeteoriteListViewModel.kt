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

    private val _fullTextSearch = MutableStateFlow("")
    val fullTextSearch: StateFlow<String> = _fullTextSearch


    val meteorites: Flow<PagingData<Meteorite>> = _fullTextSearch.flatMapLatest { searchText ->
        getMeteoriteListUseCase(searchText).cachedIn(viewModelScope)
    }

    fun setFullTextSearch(text: String) {
        _fullTextSearch.value = text
    }



}