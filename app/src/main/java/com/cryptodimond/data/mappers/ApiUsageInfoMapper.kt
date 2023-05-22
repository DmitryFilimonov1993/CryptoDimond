package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.ApiUsageInfoDTO
import com.cryptodimond.domain.util.apikey.ApiUsageInfo

fun ApiUsageInfoDTO.toApiUsageInfo(): ApiUsageInfo {
    return ApiUsageInfo(
        currentMonthlyLimit = planUsage.creditLimitMonthly,
        creditDailyUsed = currentUsage.currentDay.creditsUsed,
        creditDailyLeft = currentUsage.currentDay.creditsLeft,
        creditMonthlyUsed = currentUsage.currentMonth.creditsUsed,
        creditMonthlyLeft = currentUsage.currentMonth.creditsLeft
    )
}
