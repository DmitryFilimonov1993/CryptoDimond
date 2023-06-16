package com.cryptodimond.presentation.ui.categorydetails

import com.cryptodimond.base.Reducer

internal class CategoryDetailsReducer(
    initial: CategoryDetailsState
) : Reducer<CategoryDetailsState, CategoryDetailsUiEvent>(initial) {

    override fun reduce(oldState: CategoryDetailsState, event: CategoryDetailsUiEvent) {

        when (event) {

            is CategoryDetailsUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )

            is CategoryDetailsUiEvent.ShowData -> setState(
                oldState.copy(
                    exchangeInfo = event.exchangeInfo,
                    isLoading = false,
                    error = null
                )
            )

            is CategoryDetailsUiEvent.ShowError -> setState(
                oldState.copy(
                    exchangeInfo = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}
