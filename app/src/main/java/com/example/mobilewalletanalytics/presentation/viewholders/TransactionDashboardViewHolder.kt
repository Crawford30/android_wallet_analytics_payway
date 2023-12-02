package com.example.mobilewalletanalytics.presentation.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.data.models.CategoryBreakdown
import com.example.mobilewalletanalytics.utils.formatNumberToThousands
import com.google.android.material.textview.MaterialTextView

class TransactionDashboardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val categoryTextView: MaterialTextView = view.findViewById(R.id.category_label)
    private val transactionWithdrawalAmountTextView: MaterialTextView =
        view.findViewById(R.id.withdrawal_transaction_amount)
    private val transactionDepositAmountTextView: MaterialTextView =
        view.findViewById(R.id.deposit_transaction_amount)
    private val imagePlaceholder: ImageView = view.findViewById(R.id.dashboard_image_place_holder)


    fun bindTo(
        transaction: CategoryBreakdown?,
        onItemClicked: (transaction: CategoryBreakdown) -> Unit
    ) {
        if (transaction != null) {
//            val categoryBreakdown = transaction.get(absoluteAdapterPosition)
            categoryTextView.text = transaction.category


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

            transactionDepositAmountTextView.text =
                "${formatNumberToThousands(transaction.deposit_amount.toLong())} UGX"
            transactionDepositAmountTextView.setTextColor(Color.GREEN)
            transactionWithdrawalAmountTextView.text =
                "-${formatNumberToThousands(transaction.withdrawal_amount.toLong())} UGX"
            transactionWithdrawalAmountTextView.setTextColor(Color.RED)

        } else {
            categoryTextView.text = ""
            transactionDepositAmountTextView.text = ""
            transactionWithdrawalAmountTextView.text = ""
        }
        absoluteAdapterPosition
    }

    companion object {
        fun create(parent: ViewGroup): TransactionDashboardViewHolder {
            return TransactionDashboardViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_dashboard_layout, parent, false)
            )
        }
    }

}

