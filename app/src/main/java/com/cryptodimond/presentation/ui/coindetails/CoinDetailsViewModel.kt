package com.cryptodimond.presentation.ui.coindetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.Resource
import com.cryptodimond.presentation.ui.bottomnav.DestinationOneArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val repository: ICryptoRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CoinDetailsState, CoinDetailsUiEvent>() {

    private val reducer = CoinDetailsReducer(CoinDetailsState.init())

    override val state: StateFlow<CoinDetailsState>
        get() = reducer.state


    val retriveID = savedStateHandle.get<String>(DestinationOneArg).orEmpty()

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

    private fun sendEvent(event: CoinDetailsUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCoinInfo(id: String) {
        viewModelScope.launch {
            sendEvent(CoinDetailsUiEvent.LoadData)
            when (val result = repository.getCoinMetadata(id)) {
                is Resource.Success -> {
                    sendEvent(CoinDetailsUiEvent.ShowData(result.data!!))
                }
                is Resource.Error -> {
                    sendEvent(CoinDetailsUiEvent.ShowError(result.message!!))
                }
            }
        }
    }
}
