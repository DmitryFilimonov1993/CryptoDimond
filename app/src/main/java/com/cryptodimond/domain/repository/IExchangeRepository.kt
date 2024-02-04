package com.cryptodimond.domain.repository

import com.cryptodimond.data.model.mapper.toExchangesDetails
import com.cryptodimond.data.model.mapper.toExchangesListInfo
import com.cryptodimond.data.model.mapper.toListIds
import com.cryptodimond.base.CryptoApi
import com.cryptodimond.domain.model.Resource
import com.cryptodimond.domain.model.exchanges.ExchangesInfo
import javax.inject.Inject

interface IExchangeRepository {

    suspend fun getExchangesList(): Resource<List<ExchangesInfo>>

    suspend fun getExchangesTopList(cryptoId: String): Resource<List<ExchangesInfo>>

    suspend fun getExchangesDetails(id: String): Resource<ExchangesInfo>
}

class ExchangeRepository @Inject constructor(
    private val api: CryptoApi
) : IExchangeRepository {
    override suspend fun getExchangesList(): Resource<List<ExchangesInfo>> {
        return try {

            val listIds = api.getExchangesInfo().data?.toListIds()
            val resultString = listIds?.joinToString(separator = ",")

            Resource.Success(
                data = api.getExchangesListBy(resultString.orEmpty()).toExchangesListInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getExchangesTopList(cryptoId: String): Resource<List<ExchangesInfo>> {
        return try {

            val listIds = api.getExchangesTopListInfo(cryptoId).data?.toListIds()
            val resultString = listIds?.joinToString(separator = ",")

            Resource.Success(
                data = api.getExchangesListBy(resultString.orEmpty()).toExchangesListInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getExchangesDetails(id: String): Resource<ExchangesInfo> {
        return try {
            Resource.Success(
                data = api.getExchangesListBy(id).toExchangesDetails()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}