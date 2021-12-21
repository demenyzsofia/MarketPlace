package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.marketplace.viewmodels.products.BazaarViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation : BottomNavigationView
    private lateinit var appBar_layout : AppBarLayout
    private lateinit var  navController: NavController
    private lateinit var  topAppBar : MaterialToolbar
    private lateinit var viewModel : BazaarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(BazaarViewModel::class.java)

        initializeView()

        setNavigationIcon()
        setFragmentsTitle()

        hideBottomNavigation()
        hideAppBarLayout()


        initMenuTopAppBar()
        initMenuBottomNavigation()

    }

    private fun setFragmentsTitle() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listFragment -> topAppBar.setTitle("Bazaar")
                R.id.profileSettingsFragment -> topAppBar.setTitle("Settings")
                R.id.productDetailFragment -> topAppBar.setTitle("Product detail")
                R.id.profileFragment -> topAppBar.setTitle("Profil")
                R.id.addProductFragment -> topAppBar.setTitle("Create your fare")
                R.id.orderFragment -> topAppBar.setTitle("My fares")
            }

        }
    }


    private fun setNavigationIcon() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.profileSettingsFragment -> topAppBar.setNavigationIcon(R.drawable.arrow_icon)
                R.id.listFragment -> topAppBar.setNavigationIcon(R.drawable.market_icon)
                R.id.productDetailFragment -> topAppBar.setNavigationIcon(R.drawable.arrow_icon)
                R.id.profileFragment -> topAppBar.setNavigationIcon(R.drawable.arrow_icon)
                R.id.addProductFragment -> topAppBar.setNavigationIcon(R.drawable.arrow_icon)
                R.id.orderFragment -> topAppBar.setNavigationIcon(R.drawable.arrow_icon)
            }

        }
        topAppBar.setNavigationOnClickListener(View.OnClickListener { this.onBackPressed() })
    }




    private fun hideAppBarLayout() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> appBar_layout.visibility = View.GONE
                R.id.listFragment -> appBar_layout.visibility = View.VISIBLE

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
                    viewModel.setFlag(1)
                    findNavController( R.id.nav_host_fragment).navigate(R.id.listFragment)
                    menuItem.isChecked = true
                    true
                }
//                R.id.filter -> {
//                    //findNavController( R.id.nav_host_fragment).navigate(R.id.)
//                    menuItem.isChecked = true
//                    true
//                }

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
                    findNavController( R.id.nav_host_fragment).navigate(R.id.listFragment)
                    menuItem.isChecked = true
                    true
                }
                R.id.my_market -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.myMarketFragment)
                    menuItem.isChecked = true
                    true
                }
                R.id.my_fares -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.orderFragment)
                    menuItem.isChecked = true
                    true
                }
                else ->{
                    super.onOptionsItemSelected(menuItem)
                }

            }

        }
    }

    private fun initializeView() {
        bottom_navigation = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)
        appBar_layout = findViewById(R.id.appBarLayout)
        topAppBar = findViewById(R.id.topAppBar)
//        searchEditText = findViewById(R.id.mSearchName)
//        searchEditText.visibility = View.GONE
    }
}