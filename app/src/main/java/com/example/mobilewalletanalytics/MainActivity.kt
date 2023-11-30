package com.example.mobilewalletanalytics


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * This app has one activity and multiple fragments.
 * Navigation is handled by the Navigation Component from the Jetpack Library.
 * Dependency injection is handled by Hilt.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}