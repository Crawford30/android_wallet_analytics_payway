package com.example.mobilewalletanalytics.apis

import com.example.mobilewalletanalytics.data.models.Transaction
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Api interface for performing several network requests.
 */
interface Api {

    /**
     * Fetch all user transactions.
     * Returns a list of [Transaction].
     */
    @GET("transactions")
    suspend fun fetchAllTransactions(): List<Transaction>


    /**
     * Fetch transaction past history.
     * This is done by providing the start_date and end_date query parameters
     * Returns a list of [Transaction].
     */
    @GET("transactions")
    suspend fun fetchTransactionHistory(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): List<Transaction>


    /**
     * Fetch dashboard statistics.
     * Returns a list of [Transaction].
     */
    @GET("dashboard")
    suspend fun fetchDashboardStatistics(): List<Transaction>

}