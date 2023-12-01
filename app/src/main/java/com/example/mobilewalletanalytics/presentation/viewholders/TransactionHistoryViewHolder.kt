package com.example.mobilewalletanalytics.presentation.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.utils.formatNumberToThousands
import com.example.mobilewalletanalytics.utils.formatTimestamp
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class TransactionHistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val categoryTextView: MaterialTextView = view.findViewById(R.id.category)
    private val serviceTextView: MaterialTextView = view.findViewById(R.id.service)
    private val transactionDateTextView: MaterialTextView = view.findViewById(R.id.transaction_date)
    private val transactionAmountTextView: MaterialTextView = view.findViewById(R.id.transaction_amount)
    private val imagePlaceholder: ImageView = view.findViewById(R.id.image_place_holder)

//    private val detailsBtn: MaterialButton = view.findViewById(R.id.details)


    fun bindTo(transaction: Transaction?, onItemClicked: (transaction:  Transaction) -> Unit){



        if(transaction != null){
            categoryTextView.text = transaction.category
            serviceTextView.text = transaction.service
            transactionDateTextView.text = formatTimestamp(transaction.tx_finish)

            when (transaction.category) {
                "Mobile Money" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_mobile_money)
                }
                "Utilities" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_utilities)
                }
                "Taxes" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_taxes)
                }
                "Airtime" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_airtime)
                }
                "Loans" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_taxes)
                }
                "Internet" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_internet)
                }
                "TV" -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_tv)
                }
                else -> {
                    imagePlaceholder.setImageResource(R.drawable.ic_airtime)
                }
            }




            if(transaction.type == "Deposit"){
                transactionAmountTextView.text = "${formatNumberToThousands(transaction.amount.toLong())} UGX"
                transactionAmountTextView.setTextColor(Color.GREEN)
            }else {
                transactionAmountTextView.text = "-${formatNumberToThousands(transaction.amount.toLong())} UGX"
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