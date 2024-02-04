package com.cryptodimond.presentation.ui.categoriesscreen

import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinCategoriesViewModel @Inject constructor(
    private val repository: ICryptoRepository
): BaseViewModel<CoinCategoriesState, CoinCategoriesUiEvent>() {

    private val reducer = CoinCategoriesReducer(CoinCategoriesState.init())

    override val state: StateFlow<CoinCategoriesState>
        get() = reducer.state

    init {
        loadApiUsageInfo()
    }

    fun load() {
        loadApiUsageInfo()
    }

    private fun sendEvent(event: CoinCategoriesUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadApiUsageInfo() {
        viewModelScope.launch {
            sendEvent(CoinCategoriesUiEvent.LoadData)
            when(val result = repository.getCategoriesCoin()){
                is Resource.Success -> {
                 sendEvent(CoinCategoriesUiEvent.ShowData(result.data!!))
                }

                is Resource.Error -> {
                   sendEvent(CoinCategoriesUiEvent.ShowError(result.message!!))
                }
            }
        }
    }
}