package com.cryptodimond.data.model.mapper

import com.cryptodimond.data.model.dto.CoinExchangesInfoDTO
import com.cryptodimond.data.model.dto.ExchangeDTO
import com.cryptodimond.domain.model.exchanges.ExchangesInfo
import com.cryptodimond.presentation.ui.EMPTY_STRING
import com.cryptodimond.presentation.ui.abbreviate
import com.cryptodimond.presentation.ui.parseToDateString


fun CoinExchangesInfoDTO.toExchangesListInfo(): List<ExchangesInfo> {
    return data.map {
        ExchangesInfo(
            id = it.value.id?:0,
            name = it.value.name.orEmpty(),
            logo = it.value.logo.orEmpty(),
            volumeUSD = it.value.spotVolumeUsd?.abbreviate().toString(),
            dataLaunched = it.value.dateLaunched?.parseToDateString().orEmpty(),
            link = it.value.urls?.website?.firstOrNull().orEmpty(),
            fiats = it.value.fiats?.joinToString(", ").orEmpty(),
            description = it.value.description.orEmpty(),
            visits = it.value.weeklyVisits.toString()
        )
    }
}

fun CoinExchangesInfoDTO.toExchangesDetails(): ExchangesInfo {
    return data.values.firstOrNull()?.let {
        ExchangesInfo(
            id = it.id ?: 0,
            name = it.name.orEmpty(),
            logo = it.logo.orEmpty(),
            volumeUSD = it.spotVolumeUsd?.abbreviate().toString(),
            dataLaunched = it.dateLaunched?.parseToDateString().orEmpty(),
            link = it.urls?.website?.firstOrNull().orEmpty(),
            fiats = it.fiats?.joinToString(", ").orEmpty(),
            description = it.description.orEmpty(),
            visits = it.weeklyVisits.toString()
        )
    } ?: ExchangesInfo(
        id = -1,
        name = EMPTY_STRING,
        logo = EMPTY_STRING,
        volumeUSD = EMPTY_STRING,
        dataLaunched = EMPTY_STRING,
        link = EMPTY_STRING,
        fiats = EMPTY_STRING,
        description = EMPTY_STRING,
        visits = EMPTY_STRING
    )
}

fun List<ExchangeDTO>.toListIds() = map { it.id ?: 0 }
