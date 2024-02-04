package com.cryptodimond.domain.usecase

import androidx.paging.PagingData
import com.cryptodimond.base.BaseUseCase
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.domain.repository.ICoinRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLatestCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) : BaseUseCase<Unit, Flow<PagingData<CoinDetailsInfo>>> {

    override suspend fun execute(input: Unit): Flow<PagingData<CoinDetailsInfo>> {
        return repository.getCoinMetadataList()
    }
}

class GetSortedCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) : BaseUseCase<Pair<String, String>, Flow<PagingData<CoinDetailsInfo>>> {

    override suspend fun execute(input: Pair<String, String>): Flow<PagingData<CoinDetailsInfo>> {
        return repository.getCoinMetadataList(
            sort = input.first,
            sortDirection = input.second
        )
    }
}

class GetSearchCoinsByNameUseCase @Inject constructor(
    private val repository: ICoinRepository
) : BaseUseCase<String, Flow<PagingData<CoinDetailsInfo>>> {

    override suspend fun execute(input: String): Flow<PagingData<CoinDetailsInfo>> {
        return repository.getCoinMetadataList(
            searchName = input
        )
    }
}