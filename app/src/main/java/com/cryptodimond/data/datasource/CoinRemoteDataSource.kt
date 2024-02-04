package com.cryptodimond.data.datasource

import com.cryptodimond.domain.model.Resource
import com.cryptodimond.domain.model.coin.CoinDetailsInfo

interface CoinRemoteDataSource {

    suspend fun getCoinMetadataList(
        start: Int,
        sort: String,
        sortDirection: String,
        page: Int
    ): Resource<List<CoinDetailsInfo>>

    companion object {

        const val MARKET_CAP = "market_cap"
        const val PRICE = "price"
        const val VOLUME_24H = "volume_24h"
        const val DESC = "desc"
        const val ASC = "asc"
    }
}