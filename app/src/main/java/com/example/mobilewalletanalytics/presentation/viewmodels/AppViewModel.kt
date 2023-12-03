package com.example.mobilewalletanalytics.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.data.models.TransactionDashboard
import com.example.mobilewalletanalytics.data.remote_interfaces.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * The appViewmodel handles
 */
@HiltViewModel
class AppViewModel @Inject constructor(private val remoteRepo: RemoteRepo) : ViewModel() {

    /**
     * All transactions history are fetched from here. There's no pagination here since the users are few(10)
     */
    // LiveData for transactions history
    private val _transactionsHistoryLiveData = MutableLiveData<List<Transaction>>()

    // LiveData for dashboard data
    private val _transactionsDashboardData = MutableLiveData<TransactionDashboard>()

    // LiveData for all transactions (PagingData)
    private val _allTransactionLiveData = MutableLiveData<PagingData<Transaction>>()


    val transactionLiveData: LiveData<List<Transaction>> get() = _transactionsHistoryLiveData
    val dashboardLiveData: LiveData<TransactionDashboard> get() = _transactionsDashboardData
    val allTransactions: LiveData<PagingData<Transaction>> get() = remoteRepo.fetchAllTransactions()

    init {
        viewModelScope.launch {
            _transactionsHistoryLiveData.value =
                remoteRepo.fetchTransactionsHistory("2023-10-01 08:33:57", "2023-10-01 11:29:55")
            _transactionsDashboardData.value = remoteRepo.fetchDashboardStatistics()
        }
    }


}