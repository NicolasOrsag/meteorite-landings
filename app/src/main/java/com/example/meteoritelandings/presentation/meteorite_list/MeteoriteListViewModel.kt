package com.example.meteoritelandings.presentation.meteorite_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.meteoritelandings.domain.model.Meteorite
import com.example.meteoritelandings.domain.use_case.GetFavoriteMeteoritesUseCase
import com.example.meteoritelandings.domain.use_case.GetMeteoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MeteoriteListViewModel @Inject constructor(
    private val getMeteoriteListUseCase: GetMeteoriteListUseCase,
    private val getFavoriteMeteoritesUseCase: GetFavoriteMeteoritesUseCase
) : ViewModel() {

    private val _fullTextSearch = MutableStateFlow("")
    val fullTextSearch: StateFlow<String> = _fullTextSearch

    private val _sortOption = MutableStateFlow(SortOption.NAME_ASC)
    val sortOption: StateFlow<SortOption> = _sortOption

    private val _viewFavorites = MutableStateFlow(false)
    val viewFavorites: StateFlow<Boolean> = _viewFavorites

    @OptIn(ExperimentalCoroutinesApi::class)
    val meteorites: StateFlow<PagingData<Meteorite>> = combine(
        _fullTextSearch,
        _sortOption,
        _viewFavorites
    ) { searchText, sortOption, viewFavorites ->
        Triple(searchText, sortOption, viewFavorites)
    }.flatMapLatest { (searchText, sortOption, viewFavorites) ->
        if (viewFavorites) {
            getFavoriteMeteoritesUseCase(sortOption)
                .map {
                    applyFilters(PagingData.from(it), sortOption.stringValue)
                }
                .cachedIn(viewModelScope)
        } else {
            getMeteoriteListUseCase(searchText, sortOption.stringValue)
                .map { pagingData ->
                    applyFilters(pagingData, sortOption.stringValue)
                }
                .cachedIn(viewModelScope)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())

    private fun applyFilters(
        pagingData: PagingData<Meteorite>,
        sortOption: String
    ): PagingData<Meteorite> {
        return pagingData.filter { meteorite ->
            when {
                sortOption.contains("mass") && meteorite.mass == null -> false
                sortOption.contains("year") && meteorite.year == null -> false
                else -> true
            }
        }
    }

    fun setFullTextSearch(text: String) {
        _fullTextSearch.value = text
    }

    fun toggleSortOption(ascOption: SortOption, descOption: SortOption) {
        _sortOption.value = if (_sortOption.value == ascOption) {
            descOption
        } else {
            ascOption
        }
    }

    fun setFavoriteMeteorites(value: Boolean) {
        _viewFavorites.value = value
    }


}