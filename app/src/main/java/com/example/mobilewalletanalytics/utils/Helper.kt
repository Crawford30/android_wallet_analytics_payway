package com.example.mobilewalletanalytics.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import java.text.ParseException
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
    val inputFormats = arrayOf(
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()),
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    )

    for (inputFormat in inputFormats) {
        try {
            val date = inputFormat.parse(dateString)
            if (date != null) {
                val outputFormat = if (inputFormat.toPattern() == "yyyy-MM-dd") {
                    SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
                } else {
                    SimpleDateFormat("dd/MMM/yyyy 'at' hh:mm a", Locale.getDefault())
                }
                return outputFormat.format(date)
            }
        } catch (e: ParseException) {

        }
    }

    return "Invalid Date"
}


/**
 * Generate Placeholders from Sentence for example Airtel Internet would be AI
 */
fun generateAbbreviation(name: String): String {
    val words = name.split(" ")
    return words.joinToString("") { it.take(1).toUpperCase() }
}


/**
 * Adding top decoration to recycler view
 */
class TopSpacingItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = padding

    }
}
