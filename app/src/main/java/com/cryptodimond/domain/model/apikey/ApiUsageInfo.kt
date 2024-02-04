package com.cryptodimond.domain.model.apikey

data class ApiUsageInfo(
    val currentMonthlyLimit: Int,
    val creditDailyUsed: Int,
    val creditDailyLeft: Int,
    val creditMonthlyUsed: Int,
    val creditMonthlyLeft: Int,
    val creditMonthlyReset: String,
)
