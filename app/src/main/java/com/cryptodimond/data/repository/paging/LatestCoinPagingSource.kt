package com.cryptodimond.data.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cryptodimond.data.datasource.CoinRemoteDataSource
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.presentation.ui.EMPTY_STRING
import java.io.IOException
import retrofit2.HttpException

class LatestCoinPagingSource(
    private val remoteDataSource: CoinRemoteDataSource,
    private val sortType: String = EMPTY_STRING,
    private val sortDirection: String = EMPTY_STRING,
    private val searchName: String = EMPTY_STRING
) : PagingSource<Int, CoinDetailsInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinDetailsInfo> {
        return try {
            val page = params.key ?: 1
            val size = params.loadSize
            val from = page * size
            val latestCoin = remoteDataSource.getCoinMetadataList(
                start = from,
                page = size,
                sort = sortType,
                sortDirection = sortDirection
                )
            val a = latestCoin.data?.filter { it.name.contains(searchName) }.orEmpty()
            Log.i("TEEEEEST", "searchName = $searchName load viewmodel a =${a.map { it.name }}")
            LoadResult.Page(
                data = latestCoin.data?.filter { it.name.contains(searchName) }.orEmpty(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (latestCoin.data.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CoinDetailsInfo>): Int? {
        return state.anchorPosition
    }

}