package com.cryptodimond.presentation.ui.exchangesscreen

import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ExchangesInfoViewModel @Inject constructor(
    private val repository: ICryptoRepository
): BaseViewModel<ExchangesInfoState, ExchangesInfoUiEvent>() {

    private val reducer = ExchangesInfoReducer(ExchangesInfoState.init())

    override val state: StateFlow<ExchangesInfoState>
        get() = reducer.state

    init {
        loadCoinInfo()
    }

    fun load() {
        loadCoinInfo()
    }
    private fun sendEvent(event: ExchangesInfoUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCoinInfo() {
        viewModelScope.launch {
            sendEvent(ExchangesInfoUiEvent.LoadData)
            when(val result = repository.getExchangesList()){
                is Resource.Success -> {
                    sendEvent(ExchangesInfoUiEvent.ShowData(result.data!!))

                }

                is Resource.Error -> {
                    sendEvent(ExchangesInfoUiEvent.ShowError(result.message!!))
                }
            }
        }
    }

}