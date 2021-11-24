package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation : BottomNavigationView
    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()

        hideBottomNavigation()

    }

    private fun hideBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> bottom_navigation.visibility = View.GONE
                R.id.listFragment -> bottom_navigation.visibility = View.VISIBLE

            }
        }
    }


    private fun initializeView() {
        bottom_navigation = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)
    }
}