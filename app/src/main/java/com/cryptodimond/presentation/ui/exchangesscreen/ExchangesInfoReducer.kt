package com.cryptodimond.presentation.ui.exchangesscreen

import com.cryptodimond.base.Reducer

internal class ExchangesInfoReducer(
    initial: ExchangesInfoState
) : Reducer<ExchangesInfoState, ExchangesInfoUiEvent>(initial) {

    override fun reduce(oldState: ExchangesInfoState, event: ExchangesInfoUiEvent) {

        when (event) {

            is ExchangesInfoUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )

            is ExchangesInfoUiEvent.ShowData -> setState(
                oldState.copy(
                    exchangeInfoList = event.exchangeInfoList,
                    isLoading = false,
                    error = null
                )
            )

            is ExchangesInfoUiEvent.ShowError -> setState(
                oldState.copy(
                    exchangeInfoList = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}
