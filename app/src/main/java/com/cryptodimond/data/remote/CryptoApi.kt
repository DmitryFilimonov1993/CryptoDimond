package com.cryptodimond.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

const val prefix = "https://pro-api.coinmarketcap.com"

interface CryptoApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoinsList(): CryptoCoinDTO

    @GET("/v2/cryptocurrency/info")
    suspend fun getCoinInfo(
        @Query("id") id: String
    )

    @GET("/v2/cryptocurrency/quotes/latest")
    suspend fun getLatestCoinsQuotes(
        @Query("id") id: String
    )

}