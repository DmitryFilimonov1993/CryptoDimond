package com.cryptodimond.domain.util.categories

import com.cryptodimond.domain.util.coin.CoinDetailsInfo

data class CategoryDetails(
    val coins: List<CoinDetailsInfo>,
    val description: String,
    val volume: String,
    val income: String,
    val title: String
)
