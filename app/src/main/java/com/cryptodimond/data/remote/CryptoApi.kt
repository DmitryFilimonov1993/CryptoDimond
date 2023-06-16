package com.cryptodimond.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoinsList(
        @Query("start") from: Int,
        @Query("limit") to: Int
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