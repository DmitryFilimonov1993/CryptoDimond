package com.cryptodimond.data.model.dto

import com.squareup.moshi.Json


data class CryptoCoinDTO(
    @field:Json(name = "data")
    val data: List<CoinDTOInfo>,
    @field:Json(name = "status")
    val status: StatusRequestDTO
)

data class CoinDTOInfo(
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
    val tags: List<String>? = emptyList(),
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

data class PlatformDTO(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "symbol")
    val symbol: String,
    @field:Json(name = "slug")
    val slug: String,
    @field:Json(name = "token_address")
    val tokenAddress: String,
)

data class QuoteDTOInfo(
    @field:Json(name = "ETH")
    val ETH: QuoteCoinInfo,
    @field:Json(name = "USD")
    val USD: QuoteCoinInfo,
    @field:Json(name = "BTC")
    val BTC: QuoteCoinInfo
)

data class QuoteCoinInfo(
    @field:Json(name = "fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double,
    @field:Json(name = "last_updated")
    val lastUpdated: String,
    @field:Json(name = "market_cap")
    val marketCap: Double? = 0.0,
    @field:Json(name = "market_cap_dominance")
    val marketCapDominance: Double,
    @field:Json(name = "percent_change_1h")
    val percentChange1h: Double,
    @field:Json(name = "percent_change_24h")
    val percentChange24h: Double,
    @field:Json(name = "percent_change_30d")
    val percentChange30d: Double,
    @field:Json(name = "percent_change_60d")
    val percentChange60d: Double,
    @field:Json(name = "percent_change_7d")
    val percentChange7d: Double,
    @field:Json(name = "percent_change_90d")
    val percentChange90d: Double,
    @field:Json(name = "price")
    val price: Double?,
    @field:Json(name = "tvl")
    val tvl: Double? = 0.0,
    @field:Json(name = "volume_24h")
    val volume24h: Double,
    @field:Json(name = "volume_change_24h")
    val volumeChange24h: Double
)
