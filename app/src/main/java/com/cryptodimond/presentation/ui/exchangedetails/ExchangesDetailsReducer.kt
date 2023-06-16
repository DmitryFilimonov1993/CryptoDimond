package com.cryptodimond.presentation.ui.exchangedetails

import com.cryptodimond.base.Reducer

internal class ExchangesDetailsReducer(
    initial: ExchangesDetailsState
) : Reducer<ExchangesDetailsState, ExchangesDetailsUiEvent>(initial) {

    override fun reduce(oldState: ExchangesDetailsState, event: ExchangesDetailsUiEvent) {

        when (event) {

            is ExchangesDetailsUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )

            is ExchangesDetailsUiEvent.ShowData -> setState(
                oldState.copy(
                    exchangeInfo = event.exchangeInfo,
                    isLoading = false,
                    error = null
                )
            )

            is ExchangesDetailsUiEvent.ShowError -> setState(
                oldState.copy(
                    exchangeInfo = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}
