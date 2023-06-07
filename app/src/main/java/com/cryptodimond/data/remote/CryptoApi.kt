package com.cryptodimond.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoinsList(
        @Query("start") from: Int,
        @Query("limit") to: Int
    ): CryptoCoinDTO

    @GET("/v1/cryptocurrency/categories")
    suspend fun getCoinCategories(): CategoriesCoinDTO

    @GET("/v1/exchange/info")
    suspend fun getExchangesListBy(@Query("id") ids: String): CoinExchangesInfoDTO

    @GET("/v1/exchange/map")
    suspend fun getExchangesInfo(): ExchangesInfoDTO

    @GET("/v2/cryptocurrency/info")
    suspend fun getCoinInfo(
        @Query("id") id: String
    )

    @GET("/v2/cryptocurrency/info")
    suspend fun getCryptoInfo()

    @GET("/v1/key/info")
    suspend fun getApyKeyInfo(): ApiUsageInfoDTOCommon
}