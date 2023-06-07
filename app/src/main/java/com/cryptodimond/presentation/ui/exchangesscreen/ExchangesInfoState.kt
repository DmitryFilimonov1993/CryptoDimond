package com.cryptodimond.presentation.ui.exchangesscreen

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.domain.util.exchanges.ExchangesInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class ExchangesInfoState(
    val exchangeInfoList: List<ExchangesInfo>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = ExchangesInfoState(
            exchangeInfoList = null,
            isLoading = true,
            error = null
        )
    }
}