package com.cryptodimond.presentation.ui.categorydetails

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.model.categories.CategoryDetails
import javax.annotation.concurrent.Immutable

@Immutable
data class CategoryDetailsState(
    val categoryDetails: CategoryDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiStateMVI {

    companion object {

        fun init() = CategoryDetailsState(
            categoryDetails = null,
            isLoading = true,
            error = null
        )
    }
}