package com.cryptodimond.presentation.ui.categorydetails

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.model.categories.CategoryDetails

sealed class CategoryDetailsUiEvent : UiEventMVI {

    object LoadData : CategoryDetailsUiEvent()
    data class ShowData(val categoryDetails: CategoryDetails) : CategoryDetailsUiEvent()
    data class ShowError(val error: String) : CategoryDetailsUiEvent()
}