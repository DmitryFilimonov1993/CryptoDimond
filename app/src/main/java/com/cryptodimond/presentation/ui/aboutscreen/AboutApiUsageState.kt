package com.cryptodimond.presentation.ui.aboutscreen

import com.cryptodimond.base.UiStateMVI
import com.cryptodimond.domain.util.apikey.ApiUsageInfo
import javax.annotation.concurrent.Immutable

@Immutable
data class AboutApiUsageState(
    val apiUsageInfo: ApiUsageInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
):UiStateMVI {

    companion object {
        fun init() = AboutApiUsageState(
            apiUsageInfo = null,
            isLoading = true,
            error = null
        )
    }
}