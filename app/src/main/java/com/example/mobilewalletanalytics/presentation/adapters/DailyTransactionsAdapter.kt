package com.example.mobilewalletanalytics.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.data.models.DailyStatistic
import com.example.mobilewalletanalytics.presentation.viewholders.DailyTransactionsViewHolder
import com.google.android.material.progressindicator.CircularProgressIndicator

/**
 * The daily transaction adapter to be used with the  [RecyclerView] for Daily Transactions
 */
class DailyTransactionsAdapter(
    private val progressIndicator: CircularProgressIndicator,
    private val onItemClicked: (transaction: DailyStatistic) -> Unit
) :
    RecyclerView.Adapter<DailyTransactionsViewHolder>() {

    private var dailyTransactionsList: List<DailyStatistic> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyTransactionsViewHolder {
        return DailyTransactionsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DailyTransactionsViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val transactionList = dailyTransactionsList[position]
        holder.bindTo(transactionList, onItemClicked)
    }




    override fun getItemCount(): Int {
        return dailyTransactionsList.size
    }

    fun submitList(list: List<DailyStatistic>) {
        dailyTransactionsList = list
        notifyDataSetChanged()
    }
}