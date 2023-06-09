package com.cryptodimond.presentation.ui.latestscreen

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class LatestCoinInfoState(
    val coinInfoList: List<CoinDetailsInfo>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = LatestCoinInfoState(
            coinInfoList = null,
            isLoading = true,
            error = null
        )
    }
}

