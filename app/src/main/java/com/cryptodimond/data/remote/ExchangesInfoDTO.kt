package com.cryptodimond.data.remote

import com.squareup.moshi.Json

data class ExchangesInfoDTO(
    @field:Json(name = "data")
    val data: List<ExchangeDTO>?,
    @field:Json(name = "status")
    val status: StatusRequestDTO?
)

data class ExchangeDTO(
    @field:Json(name = "first_historical_data")
    val firstHistoricalData: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "is_active")
    val isActive: Int?,
    @field:Json(name = "last_historical_data")
    val lastHistoricalData: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "slug")
    val slug: String?,
    @field:Json(name = "status")
    val status: String?
)

