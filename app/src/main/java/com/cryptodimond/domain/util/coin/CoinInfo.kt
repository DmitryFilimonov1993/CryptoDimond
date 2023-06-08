package com.cryptodimond.domain.util.coin

data class CoinInfo(
    val id: String,
    val name: String,
    val cap: String,
    val price: String,
    val income: Double
)

data class CoinDetailsInfo(
    val id: String,
    val name: String,
    val symbol: String,
    val cap: String,
    val price: String,
    val dataLaunched: String,
    val logo: String,
    val description: String,
    val income: Double
)