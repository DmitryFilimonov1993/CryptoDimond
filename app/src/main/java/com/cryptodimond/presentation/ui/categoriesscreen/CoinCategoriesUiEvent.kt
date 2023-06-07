package com.cryptodimond.presentation.ui.categoriesscreen

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.util.apikey.ApiUsageInfo
import com.cryptodimond.domain.util.categories.CoinCategory

sealed class CoinCategoriesUiEvent : UiEventMVI {

    object LoadData : CoinCategoriesUiEvent()
    data class ShowData(val coinCategories: List<CoinCategory>) : CoinCategoriesUiEvent()
    data class ShowError(val error: String) : CoinCategoriesUiEvent()
}