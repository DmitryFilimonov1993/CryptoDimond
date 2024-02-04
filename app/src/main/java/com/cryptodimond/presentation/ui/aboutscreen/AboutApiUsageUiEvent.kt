package com.cryptodimond.presentation.ui.aboutscreen

import com.cryptodimond.base.UiEventMVI
import com.cryptodimond.domain.model.apikey.ApiUsageInfo

sealed class AboutApiUsageUiEvent : UiEventMVI {

    object LoadData : AboutApiUsageUiEvent()
    data class ShowData(val apiUsageInfo: ApiUsageInfo) : AboutApiUsageUiEvent()
    data class ShowError(val error: String) : AboutApiUsageUiEvent()
}