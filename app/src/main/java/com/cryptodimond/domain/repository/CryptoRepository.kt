package com.cryptodimond.domain.repository

import com.cryptodimond.data.mappers.toApiUsageInfo
import com.cryptodimond.data.mappers.toCategoryDetails
import com.cryptodimond.data.mappers.toCoinCategory
import com.cryptodimond.data.mappers.toCoinDetailsInfo
import com.cryptodimond.data.mappers.toCoinDetailsInfoList
import com.cryptodimond.data.mappers.toCoinInfo
import com.cryptodimond.data.mappers.toCoinInfoQ
import com.cryptodimond.data.mappers.toExchangesDetails
import com.cryptodimond.data.mappers.toExchangesListInfo
import com.cryptodimond.data.mappers.toListIds
import com.cryptodimond.data.mappers.toListIndex
import com.cryptodimond.data.remote.CryptoApi
import com.cryptodimond.domain.util.Resource
import com.cryptodimond.domain.util.apikey.ApiUsageInfo
import com.cryptodimond.domain.util.categories.CategoryDetails
import com.cryptodimond.domain.util.categories.CoinCategory
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.domain.util.exchanges.ExchangesInfo
import javax.inject.Inject

interface ICryptoRepository {

    suspend fun getApiUsageInfo(): Resource<ApiUsageInfo>

    suspend fun getLatestCoinInfo(
        from: Int,
        to: Int
    ): Resource<List<CoinInfo>>

    suspend fun getCategoriesCoin(): Resource<List<CoinCategory>>

    suspend fun getExchangesList(): Resource<List<ExchangesInfo>>

    suspend fun getExchangesTopList(cryptoId: String): Resource<List<ExchangesInfo>>

    suspend fun getExchangesDetails(id: String): Resource<ExchangesInfo>

    suspend fun getCoinMetadata(id: String): Resource<CoinDetailsInfo>

    suspend fun getCoinCategoryDetails(id: String): Resource<CategoryDetails>

    suspend fun getCoinMetadataList(
        from: Int,
        to: Int
    ): Resource<List<CoinDetailsInfo>>

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

    override suspend fun getLatestCoinInfo(from: Int, to: Int): Resource<List<CoinInfo>> {
        return try {
            Resource.Success(
                data = api.getLatestCoinsList(from, to).data.toCoinInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getCategoriesCoin(): Resource<List<CoinCategory>> {
        return try {
            Resource.Success(
                data = api.getCoinCategories().data?.toCoinCategory() ?: emptyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

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

    override suspend fun getCoinMetadata(id: String): Resource<CoinDetailsInfo> {
        return try {
            val result = api.getCoinDetails(id).toCoinInfoQ().first()
            Resource.Success(
                data = api.getCoinDetailsInfo(id).toCoinDetailsInfo(result)
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getCoinCategoryDetails(id: String): Resource<CategoryDetails> {
        return try {
            val result = api.getCategoryDetails(id)
            val listIds = result.toListIndex().joinToString(",")

            Resource.Success(
                data = api.getLatestCoinsList(listIds).toCategoryDetails(result)
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getCoinMetadataList(from: Int, to: Int): Resource<List<CoinDetailsInfo>> {
        return try {

            val result = api.getLatestCoinsList(from, to).data
            val listIds = result.toListIds()
            val resultString = listIds.joinToString(separator = ",")

            Resource.Success(
                data = api.getCoinDetailsInfo(resultString). toCoinDetailsInfoList(result.toCoinInfo())
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
