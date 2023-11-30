package com.example.mobilewalletanalytics.data.remote_interfaces

import androidx.lifecycle.LiveData
import com.example.mobilewalletanalytics.data.models.Transaction

/**
 * An interface for all methods to be implemented for the remote repo
 */
interface RemoteRepo {

    /**
     * fetch all transactions
     */
    suspend fun fetchAllTransactions(): List<Transaction>

    /**
     * fetch  transactions history
     */
    suspend fun fetchTransactionsHistory(start_date: String, end_date: String): List<Transaction>


    /**
     * fetch all dashboard statistics
     */
    suspend fun fetchDashboardStatistics(): List<Transaction>



}