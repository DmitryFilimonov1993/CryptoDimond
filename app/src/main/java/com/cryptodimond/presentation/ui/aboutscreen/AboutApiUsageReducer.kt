package com.cryptodimond.presentation.ui.aboutscreen

import com.cryptodimond.base.Reducer

internal class AboutApiUsageReducer(
    initial: AboutApiUsageState
) : Reducer<AboutApiUsageState, AboutApiUsageUiEvent>(initial) {

    override fun reduce(oldState: AboutApiUsageState, event: AboutApiUsageUiEvent) {
        when (event) {
            is AboutApiUsageUiEvent.LoadData -> setState(
                oldState.copy(isLoading = true)
            )
            is AboutApiUsageUiEvent.ShowData -> setState(
                oldState.copy(
                    apiUsageInfo = event.apiUsageInfo,
                    isLoading = false,
                    error = null
                )
            )
            is AboutApiUsageUiEvent.ShowError -> setState(
                oldState.copy(
                    apiUsageInfo = null,
                    isLoading = false,
                    error = event.error
                )
            )
        }
    }
}