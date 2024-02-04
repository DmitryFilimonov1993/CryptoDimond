package com.cryptodimond.data.model.dto

import com.squareup.moshi.Json


data class CategoriesCoinDTO(
    @field:Json(name = "data")
    val data: List<CoinCategoryDTO>? = emptyList(),
    @field:Json(name = "status")
    val status: StatusRequestDTO?
)

data class CoinCategoryDTO(
    @field:Json(name = "avg_price_change")
    val avgPriceChange: Double? = 0.0,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "last_updated")
    val lastUpdated: String?,
    @field:Json(name = "market_cap")
    val marketCap: Double?,
    @field:Json(name = "market_cap_change")
    val marketCapChange: Double?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "num_tokens")
    val numTokens: Int?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "volume")
    val volume: Double?,
    @field:Json(name = "volume_change")
    val volumeChange: Double?
)
