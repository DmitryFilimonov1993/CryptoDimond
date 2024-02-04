package com.cryptodimond.di

import com.cryptodimond.data.repository.CoinRepository
import com.cryptodimond.domain.repository.CryptoRepository
import com.cryptodimond.domain.repository.ICoinRepository
import com.cryptodimond.domain.repository.ICryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@ExperimentalCoroutinesApi
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCryptoRepository(
        cryptoRepository: CryptoRepository
    ): ICryptoRepository

    @Binds
    @Singleton
    abstract fun bindCoinRepository(
        coinRepository: CoinRepository
    ): ICoinRepository

}