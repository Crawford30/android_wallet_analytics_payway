package com.example.mobilewalletanalytics.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * A thousand separator
 */
fun formatNumberToThousands(number: Long): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(number)
}


/**
 * Date formatter into readable form
 */
fun formatTimestamp(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MMM/yyyy 'at' hh:mm a", Locale.getDefault())

    val date = inputFormat.parse(dateString)
    return if (date != null) outputFormat.format(date) else "Invalid Date"
}

/**
 * Generate Placeholders from Sentence for example Airtel Internet would be AI
 */
fun generateAbbreviation(name: String): String {
    val words = name.split(" ")
    return words.joinToString("") { it.take(1).toUpperCase() }
}


