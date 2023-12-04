package com.example.mobilewalletanalytics


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobilewalletanalytics.databinding.ActivityMainBinding
import com.example.mobilewalletanalytics.presentation.viewmodels.AppViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * This app has one activity and multiple fragments.
 * Navigation is handled by the Navigation Component from the Jetpack Library.
 * Dependency injection is handled by Hilt.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Let the Navigation Component handle tha AppBar Config(Disabled for now)
         * as i'm Setting titles in the fragments
         *
         */
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        /**
         * Bottom navigation settings and actions
         */
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val navController: NavController = findNavController(R.id.nav_host_fragment)
            when (menuItem.itemId) {
                R.id.menuHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.menuHistory -> {
                    navController.navigate(R.id.transactionsListFragment)
                    true
                }

                R.id.menuAllTransactions -> {
                    navController.navigate(R.id.allTransactionsFragment)
                    true
                }

                R.id.menuChart -> {
                    navController.navigate(R.id.chartFragment)
                    true
                }
                else -> false
            }
        }
    }
}




