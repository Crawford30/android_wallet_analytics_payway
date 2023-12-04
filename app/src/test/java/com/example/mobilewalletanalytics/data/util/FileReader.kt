package com.example.mobilewalletanalytics.data.util

import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobilewalletanalytics.data.AppTest
import java.io.IOException
import java.io.InputStreamReader

/**
 * Used to read [Json] responses used for [unittesting] the app
 */
object FileReader {
    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as AppTest).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}