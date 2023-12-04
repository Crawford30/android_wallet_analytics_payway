package com.example.mobilewalletanalytics.data

import com.example.mobilewalletanalytics.di.app.App


class AppTest : App() {
    var url = "http://127.0.0.1:8080"

    fun getBaseUrl(): String {
        return url
    }
}
