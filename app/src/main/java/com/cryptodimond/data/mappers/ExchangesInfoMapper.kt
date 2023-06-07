package com.cryptodimond.data.mappers

import com.cryptodimond.data.remote.CoinExchangesInfoDTO
import com.cryptodimond.data.remote.ExchangeDTO
import com.cryptodimond.domain.util.exchanges.ExchangesInfo
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.parseToDateString


fun CoinExchangesInfoDTO.toExchangesListInfo(): List<ExchangesInfo> {
    return data.map {
        ExchangesInfo(
            id = it.value.id?:0,
            name = it.value.name.orEmpty(),
            logo = it.value.logo.orEmpty(),
            volumeUSD = it.value.spotVolumeUsd?.abbreviate().toString(),
            dataLaunched = it.value.dateLaunched?.parseToDateString().orEmpty()
        )
    }
}

fun List<ExchangeDTO>.toListIds() = map { it.id ?: 0 }
