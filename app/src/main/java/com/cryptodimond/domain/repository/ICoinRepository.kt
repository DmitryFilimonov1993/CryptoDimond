package com.cryptodimond.domain.repository

import androidx.paging.PagingData
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.DESC
import com.cryptodimond.data.datasource.CoinRemoteDataSource.Companion.MARKET_CAP
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.presentation.ui.EMPTY_STRING
import kotlinx.coroutines.flow.Flow

interface ICoinRepository {

//    suspend fun getLatestCoinInfo(
//        page: Int
//    ): Resource<List<CoinInfo>>
//
//    suspend fun getCategoriesCoin(): Resource<List<CoinCategory>>
//
//    suspend fun getCoinMetadata(id: String): Resource<CoinDetailsInfo>
//
//    suspend fun getCoinCategoryDetails(id: String): Resource<CategoryDetails>

    suspend fun getCoinMetadataList(
        sort: String = MARKET_CAP,
        sortDirection: String = DESC,
        searchName: String = EMPTY_STRING
    ): Flow<PagingData<CoinDetailsInfo>>
}

//class CoinRepository @Inject constructor(
//    private val api: CryptoApi
//) : ICoinRepository {
//
//    override suspend fun getLatestCoinInfo(page: Int): Resource<List<CoinInfo>> {
//        return try {
//            Resource.Success(
//                data = api.getLatestCoinsList(page = page).data.toCoinInfo()
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
//
//    override suspend fun getCategoriesCoin(): Resource<List<CoinCategory>> {
//        return try {
//            Resource.Success(
//                data = api.getCoinCategories().data?.toCoinCategory() ?: emptyList()
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
//    override suspend fun getCoinMetadata(id: String): Resource<CoinDetailsInfo> {
//        return try {
//            val result = api.getCoinDetails(id).toCoinInfoQ().first()
//            Resource.Success(
//                data = api.getCoinDetailsInfo(id).toCoinDetailsInfo(result)
//            )
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
//
//    override suspend fun getCoinCategoryDetails(id: String): Resource<CategoryDetails> {
//        return try {
//
//            val categoryDetails = api.getCategoryDetails(id)
//            val listIds = categoryDetails.toListIndex().joinToString(",")
//            Log.i("TEEEEST", "getCoinCategoryDetails id = $id listIds = $listIds")
//            val latestCoinDetails = api.getCoinDetails(listIds).toCoinInfoQ()
//            Resource.Success(
//                data = api.getCoinDetailsInfo(listIds).toCoinCategoryDetails(categoryDetails.data!!, latestCoinDetails)
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
//
//    override suspend fun getCoinMetadataList(from: Int, to: Int): Resource<List<CoinDetailsInfo>> {
//        return try {
//            val result = api.getLatestCoinsList(from, to, sort = "market_cap").data
//            val listIds = result.toListIds()
//            val resultString = listIds.joinToString(separator = ",")
//            Resource.Success(
//                data = api.getCoinDetailsInfo(resultString).toCoinDetailsInfoList(result.toCoinInfo())
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
//}