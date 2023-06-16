package com.cryptodimond.presentation.ui.exchangedetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.Resource
import com.cryptodimond.presentation.ui.bottomnav.DestinationCoinDetailsArg
import com.cryptodimond.presentation.ui.bottomnav.DestinationExchangesDetailsArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ExchangesDetailsViewModel @Inject constructor(
    private val repository: ICryptoRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ExchangesDetailsState, ExchangesDetailsUiEvent>() {

    private val reducer = ExchangesDetailsReducer(ExchangesDetailsState.init())

    override val state: StateFlow<ExchangesDetailsState>
        get() = reducer.state


    val retriveID = savedStateHandle.get<String>(DestinationExchangesDetailsArg).orEmpty()

    init {
        Log.i("TEEEEEST", "coin id = $retriveID")
        loadCoinInfo(retriveID)
    }
    fun load(id: String) {
        loadCoinInfo(retriveID)
    }

//    fun loadEvent(id: String){
//        reducer.sendEvent()
//    }

    private fun sendEvent(event: ExchangesDetailsUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCoinInfo(id: String) {
        viewModelScope.launch {
            sendEvent(ExchangesDetailsUiEvent.LoadData)
            when (val result = repository.getExchangesDetails(id)) {
                is Resource.Success -> {
                    sendEvent(ExchangesDetailsUiEvent.ShowData(result.data!!))
                }
                is Resource.Error -> {
                    sendEvent(ExchangesDetailsUiEvent.ShowError(result.message!!))
                }
            }
        }
    }
}