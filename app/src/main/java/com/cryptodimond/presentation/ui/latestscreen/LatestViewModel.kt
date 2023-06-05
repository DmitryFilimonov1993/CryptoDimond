package com.cryptodimond.presentation.ui.latestscreen

import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LatestViewModel @Inject constructor(
    private val repository: ICryptoRepository
): BaseViewModel<LatestCoinInfoState, LatestCoinInfoUiEvent>() {

    private val reducer = LatestCoinReducer(LatestCoinInfoState.init())

    override val state: StateFlow<LatestCoinInfoState>
        get() = reducer.state

    init {
        loadCoinInfo()
    }

    private fun sendEvent(event: LatestCoinInfoUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCoinInfo() {
        viewModelScope.launch {
            sendEvent(LatestCoinInfoUiEvent.LoadData)
            when(val result = repository.getLatestCoinInfo()){
                is Resource.Success -> {
                    sendEvent(LatestCoinInfoUiEvent.ShowData(result.data!!))
                }

                is Resource.Error -> {
                    sendEvent(LatestCoinInfoUiEvent.ShowError(result.message!!))
                }
            }
        }
    }

}