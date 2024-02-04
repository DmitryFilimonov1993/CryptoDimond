package com.cryptodimond.data.model.dto

import com.squareup.moshi.Json


data class CryptoCoinQuotesDTO(
    @field:Json(name = "data")
    val data: Map<String, CoinQuotesDTOInfo>,
    @field:Json(name = "status")
    val status: StatusRequestDTO
)

data class CoinQuotesDTOInfo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "symbol")
    val symbol: String,
    @field:Json(name = "slug")
    val slug: String,
    @field:Json(name = "cmc_rank")
    val cmcRank: Int?,
    @field:Json(name = "num_market_pairs")
    val numMarketPairs: Int,
    @field:Json(name = "circulating_supply")
    val circulatingSupply: Double,
    @field:Json(name = "total_supply")
    val totalSupply: Double,
    @field:Json(name = "market_cap_by_total_supply")
    val marketCapByTotalSupply: Double,
    @field:Json(name = "max_supply")
    val maxSupply: Double? = 0.0,
    @field:Json(name = "infinite_supply")
    val infiniteSupply: Boolean,
    @field:Json(name = "last_updated")
    val lastUpdated: String,
    @field:Json(name = "date_added")
    val dateAdded: String,
    @field:Json(name = "tags")
    val tags: List<TagsDTO>? = emptyList(),
    @field:Json(name = "self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double? = 1.0,
    @field:Json(name = "self_reported_market_cap")
    val selfReportedMarketCap: Double? = 1.0,
    @field:Json(name = "tvl_ratio")
    val tvlRatio: Double? = 1.0,
    @field:Json(name = "platform")
    val platform: PlatformDTO,
    @field:Json(name = "quote")
    val quote: QuoteDTOInfo,
)
data class TagsDTO(
    @field:Json(name = "slug")
    val slug: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "category")
    val category: String,
)

