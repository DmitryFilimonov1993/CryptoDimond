package com.cryptodimond.presentation.ui.latestscreen

import androidx.paging.PagingData
import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.model.coin.CoinDetailsInfo

sealed class LatestCoinInfoUiEvent : UiEventMVI {

    object LoadData : LatestCoinInfoUiEvent()
    data class ShowData(val coinInfoList: PagingData<CoinDetailsInfo>) : LatestCoinInfoUiEvent()
    data class ShowError(val error: String) : LatestCoinInfoUiEvent()
}