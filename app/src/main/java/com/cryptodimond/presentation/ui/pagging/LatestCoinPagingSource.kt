package com.cryptodimond.presentation.ui.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.domain.util.coin.CoinInfo

class LatestCoinPagingSource(
    private val repository: ICryptoRepository
) : PagingSource<Int, CoinDetailsInfo>() {

    override fun getRefreshKey(state: PagingState<Int, CoinDetailsInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinDetailsInfo> {
        return try {
            val page = params.key ?: 1
            val size = params.loadSize
            val from = page * size
            val coinInfoList = repository.getCoinMetadataList(from = from, to = size).data

            LoadResult.Page(
                data = coinInfoList!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (coinInfoList.isEmpty()) null else page + 1,
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}