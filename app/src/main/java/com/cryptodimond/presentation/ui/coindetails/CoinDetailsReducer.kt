package com.cryptodimond.presentation.ui.coindetails

import com.cryptodimond.base.Reducer

internal class CoinDetailsReducer(
    initial: CoinDetailsState
) : Reducer<CoinDetailsState, CoinDetailsUiEvent>(initial) {

    override fun reduce(oldState: CoinDetailsState, event: CoinDetailsUiEvent) {

        when (event) {

            is CoinDetailsUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )

            is CoinDetailsUiEvent.ShowData -> setState(
                oldState.copy(
                    coinInfoList = event.coinInfoList,
                    isLoading = false,
                    error = null
                )
            )

            is CoinDetailsUiEvent.ShowError -> setState(
                oldState.copy(
                    coinInfoList = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}
