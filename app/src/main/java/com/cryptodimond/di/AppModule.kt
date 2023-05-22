package com.cryptodimond.di

import android.app.Application
import android.content.res.Resources
import com.cryptodimond.BuildConfig
import com.cryptodimond.R
import com.cryptodimond.data.remote.CryptoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader("api-key", Resources.getSystem().getString(R.string.api_key))
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    fun provideResources(context: Application): Resources = context.resources
}