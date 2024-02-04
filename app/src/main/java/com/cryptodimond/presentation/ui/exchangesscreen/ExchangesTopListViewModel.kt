package com.cryptodimond.presentation.ui.exchangesscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.model.Resource
import com.cryptodimond.presentation.ui.bottomnav.DestinationExchangesTopListArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ExchangesTopListViewModel @Inject constructor(
    private val repository: ICryptoRepository,
    savedStateHandle: SavedStateHandle
): BaseViewModel<ExchangesInfoState, ExchangesInfoUiEvent>() {

    private val reducer = ExchangesInfoReducer(ExchangesInfoState.init())

    override val state: StateFlow<ExchangesInfoState>
        get() = reducer.state

    val retriveID = savedStateHandle.get<String>(DestinationExchangesTopListArg).orEmpty()

    init {
        loadCoinInfo(retriveID)
    }

    fun load() {
        loadCoinInfo(retriveID)
    }
    private fun sendEvent(event: ExchangesInfoUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCoinInfo(id: String) {
        viewModelScope.launch {
            sendEvent(ExchangesInfoUiEvent.LoadData)
            when(val result = repository.getExchangesTopList(id)){
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