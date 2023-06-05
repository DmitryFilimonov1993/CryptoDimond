package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CryptoCoinDTO
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.roundTo
import kotlin.math.roundToLong

fun CryptoCoinDTO.toCoinInfo(): List<CoinInfo> {
    return data.map {
        CoinInfo(
            name = it.symbol,
            cap = it.quote.USD.marketCap.roundTo(2).toString(),
            price = it.quote.USD.price.roundTo(2).toString(),
            income = it.quote.USD.percentChange24h.roundTo(2).toString(),
        )
    }
}