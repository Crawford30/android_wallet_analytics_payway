package com.example.mobilewalletanalytics.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.presentation.viewholders.TransactionHistoryViewHolder
import com.google.android.material.progressindicator.CircularProgressIndicator

/**
 * Paging Adapter to be used with a RecyclerView.
 * The [DIFF_CALL_BACK] is used to perform diffing to ensure only changed items are updated
 */
class TransactionsPagingAdapter (private val progressIndicator: CircularProgressIndicator,
                          private val onItemClicked: (transaction: Transaction) -> Unit) :
    PagingDataAdapter<Transaction, TransactionHistoryViewHolder>(DIFF_CALL_BACK) {

    companion object {
        val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Transaction> (){
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val post = getItem(position)
        holder.bindTo(post, onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryViewHolder {
        return TransactionHistoryViewHolder.create(parent)
    }

}