package com.example.mobilewalletanalytics.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.presentation.viewholders.TransactionDashboardViewHolder
import com.example.mobilewalletanalytics.presentation.viewholders.TransactionHistoryViewHolder
import com.google.android.material.progressindicator.CircularProgressIndicator


class TransactionHistoryAdapter (private val progressIndicator: CircularProgressIndicator,
                    private val onItemClicked: (transaction: Transaction) -> Unit) :
    RecyclerView.Adapter<TransactionHistoryViewHolder>() {

    private var transactionHistoryList: List<Transaction> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryViewHolder {
        return TransactionHistoryViewHolder.create(parent)
    }



    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val transactionList = transactionHistoryList[position]
        holder.bindTo(transactionList, onItemClicked)
    }

    override fun getItemCount(): Int {
        return transactionHistoryList.size
    }

    fun submitList(list: List<Transaction>){
        transactionHistoryList = list
        notifyDataSetChanged()
    }
}



