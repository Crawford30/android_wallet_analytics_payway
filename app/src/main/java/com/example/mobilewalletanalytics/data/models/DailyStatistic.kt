package com.example.mobilewalletanalytics.data.models


/**
 * Daily Transaction Statistics [dataclass]
 */
data class DailyStatistic(
    val total_deposits: String,
    val total_withdrawals: String,
    val transaction_date: String
)