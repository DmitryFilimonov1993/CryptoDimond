package com.cryptodimond.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cryptodimond.data.datasource.CoinRemoteDataSource
import com.cryptodimond.data.repository.paging.LatestCoinPagingSource
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.domain.repository.ICoinRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CoinRepository @Inject constructor(
    private val coinRemoteDataSource: CoinRemoteDataSource
) : ICoinRepository {

    override suspend fun getCoinMetadataList(
        sort: String,
        sortDirection: String,
        searchName: String
    ): Flow<PagingData<CoinDetailsInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 80, prefetchDistance = 30),
            pagingSourceFactory = {
               LatestCoinPagingSource(
                   remoteDataSource = coinRemoteDataSource,
                   sortType = sort,
                   sortDirection = sortDirection,
                   searchName = searchName
               )
            }
        ).flow
    }
}