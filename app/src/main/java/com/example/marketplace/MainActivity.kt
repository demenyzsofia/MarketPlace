package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation : BottomNavigationView
    private lateinit var appBar_layout : AppBarLayout
    private lateinit var  navController: NavController
    private lateinit var  topAppBar : MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()

        hideBottomNavigation()
        hideAppBarLayout()

        initMenuTopAppBar()
        initMenuBottomNavigation()

    }


    private fun initializeView() {
        bottom_navigation = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)
        appBar_layout = findViewById(R.id.appBarLayout)
        topAppBar = findViewById(R.id.topAppBar)
    }

    private fun hideAppBarLayout() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> appBar_layout.visibility = View.GONE
                R.id.listFragment -> appBar_layout.visibility = View.VISIBLE
                R.id.profileSettingsFragment -> topAppBar.setTitle("Settings")
                //other users profile screen
                R.id.profileFragment -> topAppBar.setTitle("Profile")


            }
        }
    }

    private fun hideBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> bottom_navigation.visibility = View.GONE
                R.id.listFragment -> bottom_navigation.visibility = View.VISIBLE
            }
        }
    }

    private fun initMenuTopAppBar() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when (menuItem.itemId ) {
                R.id.profile -> {
                    findNavController( R.id.nav_host_fragment).navigate(R.id.profileSettingsFragment)
                    menuItem.isChecked = true
                    true
                }
                R.id.search -> {
                    //findNavController( R.id.nav_host_fragment).navigate(R.id.)
                    menuItem.isChecked = true
                    true
                }
                R.id.filter -> {
                    //findNavController( R.id.nav_host_fragment).navigate(R.id.)
                    menuItem.isChecked = true
                    true
                }

                else ->{
                    super.onOptionsItemSelected(menuItem)
                }

            }

        }
    }




    private fun initMenuBottomNavigation() {
        bottom_navigation.setOnItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when (menuItem.itemId ) {
                R.id.timeline -> {
                    //findNavController( R.id.nav_host_fragment).navigate(R.id.)
                    menuItem.isChecked = true
                    true
                }
                R.id.my_market -> {
                    //Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.)
                    menuItem.isChecked = true
                    true
                }
                R.id.my_fares -> {
                    //Navigation.findNavController(this, R.id.nav_host_fragment) .navigate(R.id.)
                    menuItem.isChecked = true
                    true
                }
                else ->{
                    super.onOptionsItemSelected(menuItem)
                }

            }

        }
    }

}