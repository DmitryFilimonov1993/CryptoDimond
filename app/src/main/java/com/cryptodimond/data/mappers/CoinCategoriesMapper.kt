package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CategoryDetailsDTO
import com.cryptodimond.data.remote.CoinCategoryDTO
import com.cryptodimond.data.remote.CryptoCoinDTO
import com.cryptodimond.domain.util.categories.CategoryDetails
import com.cryptodimond.domain.util.categories.CoinCategory
import com.cryptodimond.presentation.ui.abbreviate

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

fun CategoryDetailsDTO.toListIndex(): List<Int> {
    return data?.coins?.map { it?.id ?: 0} ?: emptyList()
}

fun CryptoCoinDTO.toCategoryDetails(dto: CategoryDetailsDTO): CategoryDetails {
        return CategoryDetails(
            coins = emptyList(),
            description = "",
            volume = "",
            income = "",
            title = "",
        )
}