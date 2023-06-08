package com.cryptodimond.presentation.ui.latestscreen

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.pagging.LatestCoinPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LatestViewModel @Inject constructor(
    private val repository: ICryptoRepository
): BaseViewModel<LatestCoinInfoState, LatestCoinInfoUiEvent>() {

    private val reducer = LatestCoinReducer(LatestCoinInfoState.init())

    override val state: StateFlow<LatestCoinInfoState>
        get() = reducer.state

    val rec: Flow<PagingData<CoinDetailsInfo>> = Pager(
        pagingSourceFactory = { LatestCoinPagingSource(repository = repository) },
        config = PagingConfig(pageSize = 80)
    ).flow.cachedIn(viewModelScope)

    //val selectorState: StateFlow<>
    init {
        //loadCoinInfo()
    }

    fun load() {
        //loadCoinInfo()
    }
    private fun sendEvent(event: LatestCoinInfoUiEvent) {
        reducer.sendEvent(event)
    }

//    private fun loadCoinInfo() {
//        viewModelScope.launch {
//            sendEvent(LatestCoinInfoUiEvent.LoadData)
//            when(val result = repository.getLatestCoinInfo()){
//                is Resource.Success -> {
//                    sendEvent(LatestCoinInfoUiEvent.ShowData(result.data!!))
//
//                }
//
//                is Resource.Error -> {
//                    sendEvent(LatestCoinInfoUiEvent.ShowError(result.message!!))
//                }
//            }
//        }
//    }

}