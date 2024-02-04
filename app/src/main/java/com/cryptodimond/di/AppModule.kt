package com.cryptodimond.di

import android.app.Application
import android.content.res.Resources
import com.cryptodimond.BuildConfig
import com.cryptodimond.base.CryptoApi
import com.cryptodimond.data.datasource.CoinRemoteDataSource
import com.cryptodimond.data.datasource.CoinRemoteDataSourceImpl
import com.cryptodimond.domain.repository.ICoinRepository
import com.cryptodimond.domain.usecase.GetLatestCoinsUseCase
import com.cryptodimond.domain.usecase.GetSearchCoinsByNameUseCase
import com.cryptodimond.domain.usecase.GetSortedCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(retrofit: Retrofit): CryptoApi {
        return retrofit.create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader("X-CMC_PRO_API_KEY", "286177b6-0ca7-46f8-8c61-beefdc16a1aa")
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Singleton
    @Provides
    fun providesCoinRemoteDataSource(
        api: CryptoApi
    ): CoinRemoteDataSource {
        return CoinRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesGetSortedCoinsUseCase(
        coinRepository: ICoinRepository
    ): GetSortedCoinsUseCase {
        return GetSortedCoinsUseCase(coinRepository)
    }

    @Singleton
    @Provides
    fun providesGetLatestCoinsUseCase(
        coinRepository: ICoinRepository
    ): GetLatestCoinsUseCase {
        return GetLatestCoinsUseCase(coinRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchCoinsByNameUseCase(
        coinRepository: ICoinRepository
    ): GetSearchCoinsByNameUseCase {
        return GetSearchCoinsByNameUseCase(coinRepository)
    }

    @Provides
    fun provideResources(context: Application): Resources = context.resources
}