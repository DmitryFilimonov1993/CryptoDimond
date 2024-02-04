package com.cryptodimond.domain.model.categories

data class CoinCategory(
    val id: String,
    val name: String,
    val tokens: String,
    val volume: String,
    val volumeChange: String
)
