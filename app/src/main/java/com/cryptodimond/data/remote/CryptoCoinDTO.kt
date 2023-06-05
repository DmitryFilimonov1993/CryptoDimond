package com.cryptodimond.data.remote

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
    val cmcRank: Int,
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
    val selfReportedCirculatingSupply: Double? = 0.0,
    @field:Json(name = "self_reported_market_cap")
    val selfReportedMarketCap: Double? = 0.0,
    @field:Json(name = "tvl_ratio")
    val tvlRatio: Double? = 0.0,
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
    @Json(name = "ETH")
    val ETH: QuoteCoinInfo,
    @Json(name = "USD")
    val USD: QuoteCoinInfo,
    @Json(name = "BTC")
    val BTC: QuoteCoinInfo
)

data class QuoteCoinInfo(
    @Json(name = "fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double,
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "market_cap")
    val marketCap: Double,
    @Json(name = "market_cap_dominance")
    val marketCapDominance: Double,
    @Json(name = "percent_change_1h")
    val percentChange1h: Double,
    @Json(name = "percent_change_24h")
    val percentChange24h: Double,
    @Json(name = "percent_change_7d")
    val percentChange7d: Double,
    @Json(name = "price")
    val price: Double,
    @Json(name = "volume_24h")
    val volume24h: Double,
    @Json(name = "volume_change_24h")
    val volumeChange24h: Double
)