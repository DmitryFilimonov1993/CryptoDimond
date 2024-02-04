package com.cryptodimond.data.model.dto


import com.squareup.moshi.Json

data class CoinExchangesInfoDTO(
    @field:Json(name = "data")
    val data: Map<String, InnerDataInfoDTO>,
    @field:Json(name = "status")
    val status: StatusRequestDTO?
)

data class UrlsInfoDTO(
    @field:Json(name = "blog")
    val blog: List<String>?,
    @field:Json(name = "chat")
    val chat: List<String>?,
    @field:Json(name = "fee")
    val fee: List<String>?,
    @field:Json(name = "twitter")
    val twitter: List<String>?,
    @field:Json(name = "website")
    val website: List<String>?
)

data class InnerDataInfoDTO(
    @field:Json(name = "countries")
    val countries: List<Any?>?,
    @field:Json(name = "date_launched")
    val dateLaunched: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "fiats")
    val fiats: List<String?>?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "logo")
    val logo: String?,
    @field:Json(name = "maker_fee")
    val makerFee: Double?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "notice")
    val notice: Any?,
    @field:Json(name = "slug")
    val slug: String?,
    @field:Json(name = "spot_volume_last_updated")
    val spotVolumeLastUpdated: String?,
    @field:Json(name = "spot_volume_usd")
    val spotVolumeUsd: Double?,
    @field:Json(name = "tags")
    val tags: Any?,
    @field:Json(name = "taker_fee")
    val takerFee: Double?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "urls")
    val urls: UrlsInfoDTO?,
    @field:Json(name = "weekly_visits")
    val weeklyVisits: Int?
)