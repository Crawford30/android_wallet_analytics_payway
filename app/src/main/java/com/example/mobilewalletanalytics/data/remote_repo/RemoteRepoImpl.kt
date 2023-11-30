package com.example.mobilewalletanalytics.data.remote_repo

import com.example.mobilewalletanalytics.apis.Api
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.data.remote_interfaces.RemoteRepo
import javax.inject.Inject

/**
 * An implementation of the [RemoteRepo] interface.
 * It uses an instance of [Api] to fetch data.
 */
class RemoteRepoImpl @Inject constructor(val api: Api) : RemoteRepo {

    /**
     * Performs an api call to fetch all transactions.
     * Returns a list of transaction.
     */
    override suspend fun fetchAllTransactions(): List<Transaction> {
      return api.fetchAllTransactions()
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
    override suspend fun fetchDashboardStatistics(): List<Transaction> {
        return api.fetchDashboardStatistics()
    }

}
