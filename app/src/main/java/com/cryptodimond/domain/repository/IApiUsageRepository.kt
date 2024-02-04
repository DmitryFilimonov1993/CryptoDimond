package com.cryptodimond.domain.repository

import com.cryptodimond.data.model.mapper.toApiUsageInfo
import com.cryptodimond.base.CryptoApi
import com.cryptodimond.domain.model.Resource
import com.cryptodimond.domain.model.apikey.ApiUsageInfo
import javax.inject.Inject

interface IApiUsageRepository {

    suspend fun getApiUsageInfo(): Resource<ApiUsageInfo>
}

class ApiUsageRepository @Inject constructor(
    private val api: CryptoApi
) : IApiUsageRepository {

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