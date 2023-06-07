package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CoinDTOInfo
import com.cryptodimond.data.remote.CryptoCoinDTO
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.roundTo
import kotlin.math.roundToLong

fun List<CoinDTOInfo>.toCoinInfo(): List<CoinInfo> {
    return map {
        CoinInfo(
            id = it.id.toString(),
            name = it.symbol,
            cap = it.quote.USD.marketCap?.abbreviate().orEmpty(),
            price = it.quote.USD.price.roundTo(2).toString(),
            income = it.quote.USD.percentChange24h.roundTo(2).toString(),
        )
    }
}