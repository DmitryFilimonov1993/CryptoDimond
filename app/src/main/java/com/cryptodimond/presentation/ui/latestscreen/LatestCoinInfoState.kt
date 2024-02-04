package com.cryptodimond.presentation.ui.latestscreen

import androidx.paging.PagingData
import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class LatestCoinInfoState(
    val coinInfoList: PagingData<CoinDetailsInfo> = PagingData.empty(),
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = LatestCoinInfoState(
            coinInfoList = PagingData.empty(),
            isLoading = true,
            error = null
        )
    }
}

