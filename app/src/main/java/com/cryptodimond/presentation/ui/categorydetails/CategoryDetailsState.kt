package com.cryptodimond.presentation.ui.categorydetails

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.util.exchanges.ExchangesInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class CategoryDetailsState(
    val exchangeInfo: ExchangesInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = CategoryDetailsState(
            exchangeInfo = null,
            isLoading = true,
            error = null
        )
    }
}