package com.cryptodimond.presentation.ui.exchangedetails

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.domain.util.exchanges.ExchangesInfo

sealed class ExchangesDetailsUiEvent : UiEventMVI {

    object LoadData : ExchangesDetailsUiEvent()
    data class ShowData(val exchangeInfo: ExchangesInfo) : ExchangesDetailsUiEvent()
    data class ShowError(val error: String) : ExchangesDetailsUiEvent()
}