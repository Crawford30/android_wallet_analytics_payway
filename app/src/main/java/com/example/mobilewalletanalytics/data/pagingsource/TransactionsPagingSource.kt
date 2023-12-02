package com.example.mobilewalletanalytics.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobilewalletanalytics.apis.Api
import com.example.mobilewalletanalytics.data.models.Transaction
import retrofit2.HttpException
import java.io.IOException

/**
 * Support pagination so that data is loaded in chunks. Paging Library 3 is used
 * Since the transactions data is very big, its better to use paging for good user experience
 */
class TransactionsPagingSource(val api: Api) : PagingSource<Int, Transaction>(){
    override fun getRefreshKey(state: PagingState<Int, Transaction>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Transaction> {
        val currentKey = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val transactions = api.fetchAllTransactions(currentKey, pageSize)
            val nextKey = if (transactions.isEmpty()) null else transactions.size
            LoadResult.Page(
                data = transactions,
                prevKey = null,
                nextKey = nextKey
            )

        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}