package com.example.mobilewalletanalytics.utils

import okhttp3.ResponseBody


/**
 * This resource file is used to handle request response from the server
 * It works for [Loading] state, [Success] response and [Failure] response
 *
 */
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}