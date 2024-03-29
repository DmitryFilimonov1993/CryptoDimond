package com.cryptodimond.presentation.ui.coindetails

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class CoinDetailsState(
    val coinInfoList: CoinDetailsInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = CoinDetailsState(
            coinInfoList = null,
            isLoading = false,
            error = null
        )
    }
}

