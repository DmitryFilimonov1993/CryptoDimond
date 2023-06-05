package com.cryptodimond.presentation.ui.latestscreen

import com.cryptodimond.base.Reducer

internal class LatestCoinReducer(
    initial: LatestCoinInfoState
) : Reducer<LatestCoinInfoState, LatestCoinInfoUiEvent>(initial) {

    override fun reduce(oldState: LatestCoinInfoState, event: LatestCoinInfoUiEvent) {

        when (event) {

            is LatestCoinInfoUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )

            is LatestCoinInfoUiEvent.ShowData -> setState(
                oldState.copy(
                    coinInfoList = event.coinInfoList,
                    isLoading = false,
                    error = null
                )
            )

            is LatestCoinInfoUiEvent.ShowError -> setState(
                oldState.copy(
                    coinInfoList = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}
