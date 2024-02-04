package com.cryptodimond.data.datasource

import com.cryptodimond.base.CryptoApi
import com.cryptodimond.data.model.mapper.toCoinDetailsInfoList
import com.cryptodimond.data.model.mapper.toCoinInfo
import com.cryptodimond.data.model.mapper.toListIds
import com.cryptodimond.domain.model.Resource
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import javax.inject.Inject

class CoinRemoteDataSourceImpl @Inject constructor(
    private val api: CryptoApi
) : CoinRemoteDataSource {

    override suspend fun getCoinMetadataList(
        start: Int,
        sort: String,
        sortDirection: String,
        page: Int
    ): Resource<List<CoinDetailsInfo>> {
        return try {
            val latestCoinsList = api.getLatestCoinsList(
                from = start,
                page = page,
                sort = sort,
                sortDirection = sortDirection).data
            val resultString = latestCoinsList.toListIds().joinToString(separator = ",")
            val result = api.getCoinDetailsInfo(resultString).toCoinDetailsInfoList(latestCoinsList.toCoinInfo())
            Resource.Success(data = result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}