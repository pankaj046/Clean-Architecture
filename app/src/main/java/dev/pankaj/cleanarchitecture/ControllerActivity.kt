package dev.pankaj.cleanarchitecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.pankaj.cleanarchitecture.databinding.ActivityControllerBinding

class ControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.startFragment, R.id.loginFragment -> {
                    if (!isUserLoggedIn()) {
                        supportActionBar?.hide()
                        binding.appbar.visibility = View.GONE
                        binding.navView.visibility = View.GONE
                    } else {
                        navigateToHomeFragment(navController)
                    }
                }
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications -> {
                    if (isUserLoggedIn()) {
                        supportActionBar?.show()
                        binding.navView.visibility = View.VISIBLE
                        binding.appbar.visibility = View.VISIBLE

                    } else {
                        navigateToStartFragment(navController)
                    }
                }
                else -> {
                    supportActionBar?.show()
                    binding.navView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // Method to check if user is logged in
    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        return false
    }

    private fun navigateToHomeFragment(navController: NavController) {
        navController.navigate(R.id.navigation_home)
    }

    private fun navigateToStartFragment(navController: NavController) {
        navController.navigate(R.id.startFragment)
    }
}


