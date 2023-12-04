package com.example.mobilewalletanalytics.di.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.HiltAndroidApp
import java.io.File


/**
 * Custom Class providing Global application state
 */
@HiltAndroidApp
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}