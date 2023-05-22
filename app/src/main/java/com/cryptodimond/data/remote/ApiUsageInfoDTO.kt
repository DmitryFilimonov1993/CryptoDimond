package com.cryptodimond.data.remote

import com.squareup.moshi.Json

data class ApiUsageInfoDTO(
    @field:Json(name = "plan")
    val planUsage: CurrentApiPlanUsageDTO,
    @field:Json(name = "usage")
    val currentUsage: CurrentApiUsageDTO
) {
}

data class CurrentApiPlanUsageDTO(
    @field:Json(name = "credit_limit_monthly")
    val creditLimitMonthly: Int,
    @field:Json(name = "credit_limit_monthly_reset")
    val creditLimitMonthlyReset: String,
)

data class CurrentApiUsageDTO(
    @field:Json(name = "current_day")
    val currentDay: CurrentCreditsDTO,
    @field:Json(name = "current_month")
    val currentMonth: CurrentCreditsDTO
)

data class CurrentCreditsDTO(
    @field:Json(name = "credits_used")
    val creditsUsed: Int,
    @field:Json(name = "credits_left")
    val creditsLeft: Int
)