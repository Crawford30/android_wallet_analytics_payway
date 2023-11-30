package com.example.mobilewalletanalytics.presentation.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.data.models.Transaction
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class TransactionHistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val categoryTextView: MaterialTextView = view.findViewById(R.id.category)
    private val serviceTextView: MaterialTextView = view.findViewById(R.id.service)
    private val transactionDateTextView: MaterialTextView = view.findViewById(R.id.transaction_date)
    private val transactionAmountTextView: MaterialTextView = view.findViewById(R.id.transaction_amount)

//    private val detailsBtn: MaterialButton = view.findViewById(R.id.details)


    fun bindTo(transaction: Transaction?, onItemClicked: (transaction:  Transaction) -> Unit){

        if(transaction != null){
            categoryTextView.text = transaction.category
            serviceTextView.text = transaction.service
            transactionDateTextView.text = transaction.transactionDate

            if(transaction.type == "Deposit"){
                transactionAmountTextView.text = "${transaction.amount} UGX"
                transactionAmountTextView.setTextColor(Color.GREEN)
            }else {
                transactionAmountTextView.text = "-${transaction.amount} UGX"
                transactionAmountTextView.setTextColor(Color.RED)
            }


//            detailsBtn.setOnClickListener {
//                onItemClicked.invoke(user)
//            }
        }else{
            categoryTextView.text = ""
            serviceTextView.text = ""
            transactionDateTextView.text = ""
            transactionAmountTextView.text = ""
        }
        absoluteAdapterPosition
    }

    companion object {
        fun create(parent: ViewGroup): TransactionHistoryViewHolder {
            return TransactionHistoryViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
            )
        }
    }
}