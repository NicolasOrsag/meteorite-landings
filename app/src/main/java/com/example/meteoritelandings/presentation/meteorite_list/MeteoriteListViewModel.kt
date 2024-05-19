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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject



@HiltViewModel
class MeteoriteListViewModel @Inject constructor(
    private val getMeteoriteListUseCase: GetMeteoriteListUseCase,
) : ViewModel() {

    private val _fullTextSearch = MutableStateFlow("")
    val fullTextSearch: StateFlow<String> = _fullTextSearch

    private val _sortOption = MutableStateFlow(SortOption.NAME_ASC)
    val sortOption: StateFlow<SortOption> = _sortOption


    val meteorites: Flow<PagingData<Meteorite>> = combine(
        _fullTextSearch,
        _sortOption
    ) { searchText, sortOption ->
        Pair(searchText, sortOption)
    }.flatMapLatest { (searchText, sortOption) ->
        getMeteoriteListUseCase(searchText, sortOption.stringValue).cachedIn(viewModelScope)
    }
    fun setFullTextSearch(text: String) {
        _fullTextSearch.value = text
    }

    fun setSortOption(option: SortOption) {
        _sortOption.value = option
    }


}