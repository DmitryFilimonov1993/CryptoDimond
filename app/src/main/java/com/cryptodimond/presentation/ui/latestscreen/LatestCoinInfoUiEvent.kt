package com.cryptodimond.presentation.ui.latestscreen

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo

sealed class LatestCoinInfoUiEvent : UiEventMVI {

    object LoadData : LatestCoinInfoUiEvent()
    data class ShowData(val coinInfoList: List<CoinDetailsInfo>) : LatestCoinInfoUiEvent()
    data class ShowError(val error: String) : LatestCoinInfoUiEvent()
}