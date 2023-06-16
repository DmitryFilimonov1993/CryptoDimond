package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CategoryDetailsDTO
import com.cryptodimond.data.remote.CoinDTOInfo
import com.cryptodimond.data.remote.CoinQuotesDTOInfo
import com.cryptodimond.data.remote.CryptoCoinDetailsDTO
import com.cryptodimond.data.remote.CryptoCoinQuotesDTO
import com.cryptodimond.domain.util.categories.CategoryDetails
import com.cryptodimond.domain.util.categories.CoinCategory
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.parseToDateString
import com.cryptodimond.presentation.ui.roundTo

fun List<CoinDTOInfo>.toCoinInfo(): List<CoinInfo> {
    return map {
        CoinInfo(
            id = it.id.toString(),
            name = it.symbol,
            cap = it.quote.USD.marketCap?.abbreviate().orEmpty(),
            price = it.quote.USD.price.roundTo(2).toString(),
            income = it.quote.USD.percentChange24h.roundTo(2),
        )
    }
}

fun CryptoCoinQuotesDTO.toCoinInfoQ(): List<CoinInfo> {
    return this.data.map {
        with(it.value) {
            CoinInfo(
                id = id.toString(),
                name = symbol,
                cap = quote.USD.marketCap?.abbreviate().orEmpty(),
                price = quote.USD.price.roundTo(2).toString(),
                income = quote.USD.percentChange24h.roundTo(2),
            )
        }
    }
}

fun CryptoCoinDetailsDTO.toCoinDetailsInfoList(list: List<CoinInfo>): List<CoinDetailsInfo> {
    return data.mapNotNull { data ->
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
}

fun CryptoCoinDetailsDTO.toCoinDetailsInfo(coinInfo: CoinInfo): CoinDetailsInfo {
    return with(this.data.values.first()) {
        CoinDetailsInfo(
            id = id.toString(),
            name = name.orEmpty(),
            cap = coinInfo.cap,
            price = coinInfo.price,
            income = coinInfo.income,
            symbol = symbol.orEmpty(),
            dataLaunched = dateLaunched?.parseToDateString().orEmpty(),
            logo = logo.orEmpty(),
            description = description.orEmpty()
        )
    }
}

fun CryptoCoinDetailsDTO.toCategoryDetailsInfo(details: CategoryDetailsDTO): CategoryDetails {
    return with(this.data.values.first()) {
        CategoryDetails(
            coins = this.
//
//            id = id.toString(),
//            name = name.orEmpty(),
//            cap = coinInfo.cap,
//            price = coinInfo.price,
//            income = coinInfo.income,
//            symbol = symbol.orEmpty(),
//            dataLaunched = dateLaunched?.parseToDateString().orEmpty(),
//            logo = logo.orEmpty(),
//            description = description.orEmpty()
        )
    }
}

fun List<CoinDTOInfo>.toListIds() = map { it.id ?: 0 }