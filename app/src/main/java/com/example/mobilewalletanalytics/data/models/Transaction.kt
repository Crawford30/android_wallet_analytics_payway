package com.example.mobilewalletanalytics.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Transaction  [dataclass]
 */
@Parcelize
data class Transaction(
    val id: Int,
    val tx_finish: String,
    val amount: Int,
    val type: String,
    val service: String,
    val category: String,
    val created_at: String,
    val updated_at: String
):Parcelable
