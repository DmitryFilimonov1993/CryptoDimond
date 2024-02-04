package com.cryptodimond.presentation.ui.latestscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.ASC
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.DESC
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.MARKET_CAP
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.PRICE
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.VOLUME_24H
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.domain.usecase.GetLatestCoinsUseCase
import com.cryptodimond.domain.usecase.GetSearchCoinsByNameUseCase
import com.cryptodimond.domain.usecase.GetSortedCoinsUseCase
import com.cryptodimond.presentation.ui.latestscreen.LatestViewModel.SortingButton.MarketButton
import com.cryptodimond.presentation.ui.latestscreen.LatestViewModel.SortingButton.PriceButton
import com.cryptodimond.presentation.ui.latestscreen.LatestViewModel.SortingButton.VolumeButton
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

@HiltViewModel
class LatestViewModel @Inject constructor(
    private val sortedCoinsUseCase: GetSortedCoinsUseCase,
    private val searchCoinsByNameUseCase: GetSearchCoinsByNameUseCase,
    private val latestCoinsUseCase: GetLatestCoinsUseCase
): BaseViewModel<LatestCoinInfoState, LatestCoinInfoUiEvent>() {

    private val _latestCoinsState: MutableStateFlow<PagingData<CoinDetailsInfo>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<CoinDetailsInfo>> get() = _latestCoinsState

    private val _sortingButtonsState: MutableStateFlow<Triple<MarketButton, PriceButton, VolumeButton>> =
        MutableStateFlow(
            value = Triple(
                first = MarketButton(isSelected = true, isDescDirection = true),
                second = PriceButton(isSelected = false, isDescDirection = true),
                third = VolumeButton(isSelected = false, isDescDirection = true),
            )
        )
    val sortingButtonsState: MutableStateFlow<Triple<MarketButton, PriceButton, VolumeButton>> get() = _sortingButtonsState

    private val reducer = LatestCoinReducer(LatestCoinInfoState.init())

    override val state: StateFlow<LatestCoinInfoState>
        get() = reducer.state

    init { loadInit() }

    private fun loadInit() {
        viewModelScope.launch {
            loadCoinInfo()
        }
    }
     fun search(text: String){
         viewModelScope.launch {
             searchCoinsByNameUseCase.execute(text)
                 .distinctUntilChanged()
                 .cachedIn(viewModelScope)
                 .onCompletion {
                     sendEvent(LatestCoinInfoUiEvent.ShowError(it?.message ?: "Error while sorting coin info"))
                 }
                 .collect {
                     _latestCoinsState.value = it
                 }
         }
     }
    fun onMarketButtonClicked() {
        viewModelScope.launch {
            val isDescDirection = sortingButtonsState.value.first.isDescDirection
            sortingButtonsState.value = Triple(
                first = MarketButton(isSelected = true, isDescDirection = !isDescDirection),
                second = PriceButton(isSelected = false, isDescDirection = true),
                third = VolumeButton(isSelected = false, isDescDirection = true),
            )
            sort(MARKET_CAP, if (isDescDirection) ASC else DESC)
        }
    }

    fun onPriceButtonClicked() {
        viewModelScope.launch {
            val isDescDirection = sortingButtonsState.value.second.isDescDirection
                sortingButtonsState.value = Triple(
                    first = MarketButton(isSelected = false, isDescDirection = true),
                    second = PriceButton(isSelected = true, isDescDirection = !isDescDirection),
                    third = VolumeButton(isSelected = false, isDescDirection = true),
                )
            sort(PRICE, if (isDescDirection) ASC else DESC)

        }
    }

    fun onVolumeButtonClicked() {
        viewModelScope.launch {
            val isDescDirection = sortingButtonsState.value.third.isDescDirection
                sortingButtonsState.value = Triple(
                    first = MarketButton(isSelected = false, isDescDirection = true),
                    second = PriceButton(isSelected = false, isDescDirection = true),
                    third = VolumeButton(isSelected = true, isDescDirection = !isDescDirection),
                )
            sort(VOLUME_24H, if (isDescDirection) ASC else DESC)
        }
    }

    private suspend fun sort(sortType: String, sortDirection: String) {
        sortedCoinsUseCase.execute(sortType to sortDirection)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onCompletion {
                sendEvent(LatestCoinInfoUiEvent.ShowError(it?.message ?: "Error while sorting coin info"))
            }
            .collect {
                _latestCoinsState.value = it
            }
    }

    private suspend fun loadCoinInfo() {
        Log.i("TEEEEST", "loadCoinInfo was called")
        latestCoinsUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                Log.i("TEEEEST", "collect was called it = ${it.map { it.id }}")
                _latestCoinsState.value = it
            }

    }
    private fun sendEvent(event: LatestCoinInfoUiEvent) {
        reducer.sendEvent(event)
    }

sealed class SortingButton(open val isSelected: Boolean, open val isDescDirection: Boolean) {
    data class MarketButton(
        override val isSelected: Boolean,
        override val isDescDirection: Boolean
    ) : SortingButton(isSelected, isDescDirection)

    data class PriceButton(
        override val isSelected: Boolean,
        override val isDescDirection: Boolean
    ) : SortingButton(isSelected, isDescDirection)

    data class VolumeButton(
        override val isSelected: Boolean,
        override val isDescDirection: Boolean
    ) : SortingButton(isSelected, isDescDirection)
}
}