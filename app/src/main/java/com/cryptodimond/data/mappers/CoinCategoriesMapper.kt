package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CoinCategoryDTO
import com.cryptodimond.domain.util.categories.CoinCategory
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.roundTo

fun List<CoinCategoryDTO>.toCoinCategory(): List<CoinCategory> {
    return sortedByDescending { it.volume }
        .map {
        CoinCategory(
            id = it.id.orEmpty(),
            name = it.name.orEmpty(),
            tokens = it.numTokens.toString(),
            volume = it.volume?.abbreviate().orEmpty(),
            volumeChange = it.volumeChange?.abbreviate().orEmpty(),
        )
    }
}