package com.example.mobilewalletanalytics.data.models

/**
 * Transaction Dashboard [dataclass]
 */
data class TransactionDashboard(
    val category_breakdown: List<CategoryBreakdown>,
    val daily_statistics: List<DailyStatistic>,
    val total_deposits: String,
    val total_withdrawals: String
)