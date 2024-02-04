package com.cryptodimond.domain.model.categories

import com.cryptodimond.domain.model.coin.CoinDetailsInfo

data class CategoryDetails(
    val coins: List<CoinDetailsInfo>,
    val description: String,
    val volume: String,
    val income: Double,
    val title: String
)
