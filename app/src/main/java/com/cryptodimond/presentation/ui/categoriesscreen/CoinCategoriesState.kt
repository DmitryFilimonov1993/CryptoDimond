package com.cryptodimond.presentation.ui.categoriesscreen

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.model.categories.CoinCategory
import javax.annotation.concurrent.Immutable

@Immutable
data class CoinCategoriesState(
    val coinCategories: List<CoinCategory>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
):UiStateMVI {

    companion object {
        fun init() = CoinCategoriesState(
            coinCategories = null,
            isLoading = true,
            error = null
        )
    }
}