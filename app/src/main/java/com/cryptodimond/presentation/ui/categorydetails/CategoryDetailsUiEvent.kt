package com.cryptodimond.presentation.ui.categorydetails

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.exchanges.ExchangesInfo

sealed class CategoryDetailsUiEvent : UiEventMVI {

    object LoadData : CategoryDetailsUiEvent()
    data class ShowData(val exchangeInfo: ExchangesInfo) : CategoryDetailsUiEvent()
    data class ShowError(val error: String) : CategoryDetailsUiEvent()
}