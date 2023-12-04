package com.example.mobilewalletanalytics.data.remote_repo

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.mobilewalletanalytics.apis.Api
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.data.models.TransactionDashboard
import com.example.mobilewalletanalytics.data.pagingsource.TransactionsPagingSource
import com.example.mobilewalletanalytics.data.remote_interfaces.RemoteRepo
import javax.inject.Inject

/**
 * An implementation of the [RemoteRepo] interface.
 * It uses an instance of [Api] to fetch data.
 */
class RemoteRepoImpl @Inject constructor(val api: Api) : RemoteRepo {

    /**
     * Configuration for the [PagingSource]
     */
    private val pagingConfig = PagingConfig(
        pageSize = 30
    )


    /**
     * Performs an api call to fetch all transactions (which are paged).
     * Returns a list of transaction.
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    override fun fetchAllTransactions(): LiveData<PagingData<Transaction>> {
        return Pager(
            pagingConfig
        ){
            TransactionsPagingSource(api)
        }.liveData
    }

    /**
     * Performs an api call to fetch all past transactions/transactions history
     * based on start_date and end_date query parameter.
     * Returns a list of transaction.
     */
    override suspend fun fetchTransactionsHistory(
        start_date: String,
        end_date: String
    ): List<Transaction> {
       return api.fetchTransactionHistory(start_date, end_date)
    }

    /**
     * Performs an api call to fetch dashboard statistics data.
     * Returns a list of transaction.
     */
    override suspend fun fetchDashboardStatistics(): TransactionDashboard {
        return api.fetchDashboardStatistics()
    }

    /**
     * Performs an api call to fetch all the  user transactions.
     * The data is not paged here
     */
    override suspend fun fetchAllUserTransactions(): List<Transaction> {
        return  api.fetchAllData()
    }

}
