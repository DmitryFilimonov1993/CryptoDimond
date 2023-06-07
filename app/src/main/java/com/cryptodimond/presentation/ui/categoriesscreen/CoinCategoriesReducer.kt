package com.cryptodimond.presentation.ui.categoriesscreen

import com.cryptodimond.base.Reducer

class CoinCategoriesReducer(
    initial: CoinCategoriesState
) : Reducer<CoinCategoriesState, CoinCategoriesUiEvent>(initial) {

    override fun reduce(oldState: CoinCategoriesState, event: CoinCategoriesUiEvent) {
        when (event) {
            is CoinCategoriesUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )
            is CoinCategoriesUiEvent.ShowData -> setState(
                oldState.copy(
                    coinCategories = event.coinCategories,
                    isLoading = false,
                    error = null
                )
            )
            is CoinCategoriesUiEvent.ShowError -> setState(
                oldState.copy(
                    coinCategories = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}