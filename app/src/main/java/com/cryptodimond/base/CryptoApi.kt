package com.cryptodimond.base

import com.cryptodimond.data.model.dto.ApiUsageInfoDTOCommon
import com.cryptodimond.data.model.dto.CategoriesCoinDTO
import com.cryptodimond.data.model.dto.CategoryDetailsDTO
import com.cryptodimond.data.model.dto.CoinExchangesInfoDTO
import com.cryptodimond.data.model.dto.CryptoCoinDTO
import com.cryptodimond.data.model.dto.CryptoCoinDetailsDTO
import com.cryptodimond.data.model.dto.CryptoCoinQuotesDTO
import com.cryptodimond.data.model.dto.ExchangesInfoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoinsList(
        @Query("start") from: Int,
        @Query("limit") page: Int,
        @Query("sort") sort: String,
        @Query("sort_dir") sortDirection: String,
    ): CryptoCoinDTO

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoinsList(
        @Query("id") ids: String
    ): CryptoCoinDTO

    @GET("/v2/cryptocurrency/quotes/latest")
    suspend fun getCoinDetails(
        @Query("id") id: String
    ): CryptoCoinQuotesDTO

    @GET("/v1/cryptocurrency/categories")
    suspend fun getCoinCategories(): CategoriesCoinDTO

    @GET("/v1/cryptocurrency/category")
    suspend fun getCategoryDetails(
        @Query("id") id: String
    ): CategoryDetailsDTO

    @GET("/v1/exchange/info")
    suspend fun getExchangesListBy(
        @Query("id") ids: String
    ): CoinExchangesInfoDTO

    @GET("/v2/cryptocurrency/info")
    suspend fun getCoinDetailsInfo(
        @Query("id") ids: String
    ): CryptoCoinDetailsDTO

    @GET("/v1/exchange/map")
    suspend fun getExchangesInfo(): ExchangesInfoDTO

    @GET("/v1/exchange/map")
    suspend fun getExchangesTopListInfo(
        @Query("crypto_id") id: String
    ): ExchangesInfoDTO

    @GET("/v1/key/info")
    suspend fun getApyKeyInfo(): ApiUsageInfoDTOCommon
}