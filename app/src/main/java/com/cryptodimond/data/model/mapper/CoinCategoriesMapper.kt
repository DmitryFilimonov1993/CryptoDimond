package com.cryptodimond.data.model.mapper

import com.cryptodimond.data.model.dto.CategoryDetailsDTO
import com.cryptodimond.data.model.dto.CoinCategoryDTO
import com.cryptodimond.data.model.dto.CoinCategoryDetailsDTO
import com.cryptodimond.data.model.dto.CryptoCoinDetailsDTO
import com.cryptodimond.domain.model.categories.CategoryDetails
import com.cryptodimond.domain.model.categories.CoinCategory
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.domain.model.coin.CoinInfo
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.parseToDateString
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

fun CategoryDetailsDTO.toListIndex(): List<Int> {
    return data?.coins?.map { it?.id ?: 0} ?: emptyList()
}

fun CryptoCoinDetailsDTO.toCoinCategoryDetails(
    coinCategoryDetailsDTO: CoinCategoryDetailsDTO,
    list: List<CoinInfo>
): CategoryDetails {
    val coinDetailsInfo = data.mapNotNull { data ->
        val coinInfo = list.find { it.id == data.value.id.toString() }
        CoinDetailsInfo(
            id = data.value.id.toString(),
            name = data.value.name.orEmpty(),
            cap = coinInfo?.cap.orEmpty(),
            price = coinInfo?.price.orEmpty(),
            income = coinInfo?.income ?: 0.0,
            symbol = data.value.symbol.orEmpty(),
            dataLaunched = data.value.dateLaunched?.parseToDateString().orEmpty(),
            logo = data.value.logo.orEmpty(),
            description = data.value.description.orEmpty()
        )
    }
    return CategoryDetails(
        coins = coinDetailsInfo,
        description = coinCategoryDetailsDTO.description.orEmpty(),
        volume = (coinCategoryDetailsDTO.volume ?: 0.0).abbreviate(),
        income = (coinCategoryDetailsDTO.volumeChange ?: 0.0).roundTo(2),
        title = coinCategoryDetailsDTO.title.orEmpty(),
    )
}