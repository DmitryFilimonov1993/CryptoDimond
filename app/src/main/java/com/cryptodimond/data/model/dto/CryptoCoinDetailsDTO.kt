package com.cryptodimond.data.model.dto

import com.squareup.moshi.Json

data class CryptoCoinDetailsDTO(
    @field:Json(name = "data")
    val data: Map<String, InnerCoinDetailsDTO>,
    @field:Json(name = "status")
    val status: StatusRequestDTO
)

data class InnerCoinDetailsDTO(
    @field:Json(name = "category")
    val category: String?,
    @field:Json(name = "date_added")
    val dateAdded: String?,
    @field:Json(name = "date_launched")
    val dateLaunched: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "logo")
    val logo: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "platform")
    val platform: Any?,
    @field:Json(name = "slug")
    val slug: String?,
    @field:Json(name = "symbol")
    val symbol: String?,
    @field:Json(name = "tags")
    val tags: List<String>?,
    @field:Json(name = "urls")
    val urls: CoinUrls?
)

data class CoinUrls(
    @field:Json(name = "announcement")
    val announcement: List<String>?,
    @field:Json(name = "chat")
    val chat: List<String>?,
    @field:Json(name = "explorer")
    val explorer: List<String>?,
    @field:Json(name = "message_board")
    val messageBoard: List<String>?,
    @field:Json(name = "reddit")
    val reddit: List<String>?,
    @field:Json(name = "source_code")
    val sourceCode: List<String>?,
    @field:Json(name = "technical_doc")
    val technicalDoc: List<String>?,
    @field:Json(name = "twitter")
    val twitter: List<String>?,
    @field:Json(name = "website")
    val website: List<String>?
)
