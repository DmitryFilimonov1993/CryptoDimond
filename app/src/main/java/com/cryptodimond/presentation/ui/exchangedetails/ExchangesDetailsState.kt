package com.cryptodimond.presentation.ui.exchangedetails

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.domain.util.exchanges.ExchangesInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class ExchangesDetailsState(
    val exchangeInfo: ExchangesInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = ExchangesDetailsState(
            exchangeInfo = null,
            isLoading = true,
            error = null
        )
    }
}