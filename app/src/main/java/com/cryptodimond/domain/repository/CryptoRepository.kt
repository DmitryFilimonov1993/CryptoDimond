package com.cryptodimond.domain.repository

import com.cryptodimond.data.mappers.toApiUsageInfo
import com.cryptodimond.data.mappers.toCoinInfo
import com.cryptodimond.data.remote.CryptoApi
import com.cryptodimond.domain.util.Resource
import com.cryptodimond.domain.util.apikey.ApiUsageInfo
import com.cryptodimond.domain.util.coin.CoinInfo
import javax.inject.Inject

interface ICryptoRepository {

    suspend fun getApiUsageInfo(): Resource<ApiUsageInfo>

    suspend fun getLatestCoinInfo(): Resource<List<CoinInfo>>

}

class CryptoRepository @Inject constructor(
    private val api: CryptoApi
) : ICryptoRepository {

    override suspend fun getApiUsageInfo(): Resource<ApiUsageInfo> {
        return try {
            Resource.Success(
                data = api.getApyKeyInfo().data.toApiUsageInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getLatestCoinInfo(): Resource<List<CoinInfo>> {
        return try {
            Resource.Success(
                data = api.getLatestCoinsList().toCoinInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
