package com.cryptodimond.data.model.mapper

import com.cryptodimond.data.model.dto.ApiUsageInfoDTO
import com.cryptodimond.domain.model.apikey.ApiUsageInfo

fun ApiUsageInfoDTO.toApiUsageInfo(): ApiUsageInfo {
    return ApiUsageInfo(
        currentMonthlyLimit = planUsage.creditLimitMonthly,
        creditDailyUsed = currentUsage.currentDay.creditsUsed,
        creditDailyLeft = currentUsage.currentDay.creditsLeft,
        creditMonthlyUsed = currentUsage.currentMonth.creditsUsed,
        creditMonthlyLeft = currentUsage.currentMonth.creditsLeft,
        creditMonthlyReset = planUsage.creditLimitMonthlyReset
    )
}
