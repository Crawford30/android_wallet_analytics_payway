package com.example.mobilewalletanalytics.presentation.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.data.models.DailyStatistic
import com.example.mobilewalletanalytics.utils.formatNumberToThousands
import com.example.mobilewalletanalytics.utils.formatTimestamp
import com.google.android.material.textview.MaterialTextView

/**
 * Transaction daily statistics ViewHolder
 */
class DailyTransactionsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val transactionDate: MaterialTextView = view.findViewById(R.id.transaction_daily_date)
    private val transactionWithdrawalAmountTextView: MaterialTextView =
        view.findViewById(R.id.daily_withdrawal_transaction_amount)
    private val transactionDepositAmountTextView: MaterialTextView =
        view.findViewById(R.id.daily_deposit_transaction_amount)
    private val imagePlaceholder: ImageView = view.findViewById(R.id.daily_dashboard_image_place_holder)

    fun bindTo(
        transaction: DailyStatistic?,
        onItemClicked: (transaction: DailyStatistic) -> Unit
    ) {
        if (transaction != null) {
            imagePlaceholder.setImageResource(R.drawable.ic_mobile_money)
            transactionDate.text = formatTimestamp(transaction.transaction_date)
            transactionDepositAmountTextView.text = "${formatNumberToThousands(transaction.total_deposits.toLong())} UGX"
            transactionDepositAmountTextView.setTextColor(Color.GREEN)
            transactionWithdrawalAmountTextView.text = "-${formatNumberToThousands(transaction.total_withdrawals.toLong())} UGX"
            transactionWithdrawalAmountTextView.setTextColor(Color.RED)

        } else {
            transactionDate.text = ""
            transactionDepositAmountTextView.text = ""
            transactionWithdrawalAmountTextView.text = ""
        }
        absoluteAdapterPosition
    }

    companion object {
        fun create(parent: ViewGroup): DailyTransactionsViewHolder {
            return DailyTransactionsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_daily_statistics_layout, parent, false)
            )
        }
    }

}

