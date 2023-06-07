package com.cryptodimond.presentation.ui.exchangesscreen

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.domain.util.exchanges.ExchangesInfo

sealed class ExchangesInfoUiEvent : UiEventMVI {

    object LoadData : ExchangesInfoUiEvent()
    data class ShowData(val exchangeInfoList: List<ExchangesInfo>) : ExchangesInfoUiEvent()
    data class ShowError(val error: String) : ExchangesInfoUiEvent()
}