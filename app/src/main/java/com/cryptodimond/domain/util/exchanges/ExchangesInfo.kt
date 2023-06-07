package com.cryptodimond.domain.util.exchanges

data class ExchangesInfo(
    val id: Int,
    val name: String,
    val logo: String,
    val volumeUSD: String,
    val dataLaunched: String
)