package com.example.mobilewalletanalytics.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.data.models.CategoryBreakdown
import com.example.mobilewalletanalytics.presentation.viewholders.TransactionDashboardViewHolder
import com.google.android.material.progressindicator.CircularProgressIndicator

class TransactionDashboardAdapter (private val progressIndicator: CircularProgressIndicator,
                                 private val onItemClicked: (transaction: CategoryBreakdown) -> Unit) :
    RecyclerView.Adapter<TransactionDashboardViewHolder>() {

    private var transactionDashboardList: List<CategoryBreakdown> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDashboardViewHolder {
        return TransactionDashboardViewHolder.create(parent)
    }




    override fun onBindViewHolder(holder: TransactionDashboardViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val transactionList = transactionDashboardList[position]
        holder.bindTo(transactionList, onItemClicked)
    }

    override fun getItemCount(): Int {
        return transactionDashboardList.size
    }

    fun submitList(list: List<CategoryBreakdown>){
        transactionDashboardList = list
        notifyDataSetChanged()
    }
}