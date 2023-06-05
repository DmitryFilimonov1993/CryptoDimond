package com.cryptodimond.data.remote

import com.squareup.moshi.Json


data class ApiUsageInfoDTOCommon(
    @field:Json(name = "data")
    val data: ApiUsageInfoDTO,
    @field:Json(name = "status")
    val status: StatusRequestDTO
) {
}


data class ApiUsageInfoDTO(
    @field:Json(name = "plan")
    val planUsage: CurrentApiPlanUsageDTO,
    @field:Json(name = "usage")
    val currentUsage: CurrentApiUsageDTO
) {
}

data class StatusRequestDTO(
    @field:Json(name = "timestamp")
    val timestamp: String,
    @field:Json(name = "error_code")
    val errorCode: Int,
    @field:Json(name = "error_message")
    val errorMessage: String,
    @field:Json(name = "elapsed")
    val elapsed: Int,
    @field:Json(name = "credit_count")
    val creditCount: Int,
    @field:Json(name = "notice")
    val notice: String
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