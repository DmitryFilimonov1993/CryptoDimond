package com.cryptodimond.presentation.ui.aboutscreen

import com.cryptodimond.domain.util.apikey.ApiUsageInfo

data class AboutApiUsageState(
    val apiUsageInfo: ApiUsageInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
}