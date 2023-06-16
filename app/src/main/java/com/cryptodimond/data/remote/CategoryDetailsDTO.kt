package com.cryptodimond.data.remote
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


data class CategoryDetailsDTO(
    @field:Json(name = "data")
    val data: CoinCategoryDetailsDTO?,
    @field:Json(name = "status")
    val status: StatusRequestDTO?
)

data class CoinCategoryDetailsDTO(
    @field:Json(name = "avg_price_change")
    val avgPriceChange: Double?,
    @field:Json(name = "coins")
    val coins: List<Coin?>?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "last_updated")
    val lastUpdated: Long?,
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

data class Coin(
    @field:Json(name = "circulating_supply")
    val circulatingSupply: Int?,
    @field:Json(name = "cmc_rank")
    val cmcRank: Int?,
    @field:Json(name = "date_added")
    val dateAdded: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "last_updated")
    val lastUpdated: String?,
    @field:Json(name = "max_supply")
    val maxSupply: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "num_market_pairs")
    val numMarketPairs: Int?,
    @field:Json(name = "platform")
    val platform: Any?,
    @field:Json(name = "quote")
    val quote: Quote?,
    @field:Json(name = "slug")
    val slug: String?,
    @field:Json(name = "symbol")
    val symbol: String?,
    @field:Json(name = "tags")
    val tags: List<String?>?,
    @field:Json(name = "total_supply")
    val totalSupply: Int?
)

data class Quote(
    @field:Json(name = "BTC")
    val BTC: QuoteCoinInfo?,
    @field:Json(name = "ETH")
    val ETH: QuoteCoinInfo?,
    @field:Json(name = "USD")
    val USD: QuoteCoinInfo?
)