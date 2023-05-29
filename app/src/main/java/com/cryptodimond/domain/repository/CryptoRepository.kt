package com.cryptodimond.domain.repository

import com.cryptodimond.data.mappers.toApiUsageInfo
import com.cryptodimond.data.remote.CryptoApi
import com.cryptodimond.domain.util.Resource
import com.cryptodimond.domain.util.apikey.ApiUsageInfo
import javax.inject.Inject

interface ICryptoRepository {

    suspend fun getApiUsageInfo() : Resource<ApiUsageInfo>

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
}