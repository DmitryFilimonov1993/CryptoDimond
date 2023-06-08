package com.cryptodimond.presentation.ui.coindetails

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo

sealed class CoinDetailsUiEvent : UiEventMVI {

    object LoadData : CoinDetailsUiEvent()
    data class ShowData(val coinInfoList: CoinDetailsInfo) : CoinDetailsUiEvent()
    data class ShowError(val error: String) : CoinDetailsUiEvent()
}