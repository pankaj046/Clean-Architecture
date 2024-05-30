package dev.pankaj.cleanarchitecture

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.pankaj.cleanarchitecture.data.local.prefmanager.SharedPreferencesUtil
import dev.pankaj.cleanarchitecture.databinding.ActivityControllerBinding
import dev.pankaj.cleanarchitecture.extensions.hide
import dev.pankaj.cleanarchitecture.extensions.show
import javax.inject.Inject


@AndroidEntryPoint
class ControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControllerBinding
    private var isCartVisible = true
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil
    private var navController: NavController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_profile
            )
        )
        navController?.let {
            setupActionBarWithNavController(it, appBarConfiguration)
            navView.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.startFragment, R.id.loginFragment, R.id.permissionFragment -> {
                    if (!isUserLoggedIn()) {
                        supportActionBar?.hide()
                        binding.appbar.hide()
                        binding.navView.hide()
                    } else {
                        navigateToHomeFragment(controller, arguments)
                    }
                }
                R.id.navigation_home, R.id.navigation_profile -> {
                    if (isUserLoggedIn()) {
                        supportActionBar?.show()
                        binding.navView.show()
                        binding.appbar.show()
                        updateCartStatus(true)
                    } else {
                        navigateToStartFragment(controller)
                    }
                }
                R.id.navigation_cart -> {
                    updateCartStatus(false)
                    binding.navView.hide()
                }
                else -> {
                    updateCartStatus(false)
                    supportActionBar?.show()
                    binding.navView.show()
                    binding.appbar.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun updateCartStatus(show: Boolean) {
        isCartVisible = show
        invalidateMenu()
    }

    private fun isUserLoggedIn(): Boolean {
        return sharedPreferencesUtil.containKey("token")
    }

    private fun navigateToHomeFragment(navController: NavController, arguments: Bundle?) {
        navController.navigate(R.id.navigation_home, arguments)
    }

    private fun navigateToStartFragment(navController: NavController) {
        navController.navigate(R.id.startFragment)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu?.findItem(R.id.action_cart)
        val actionView = menuItem?.actionView
        menuItem?.isVisible = isCartVisible
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                navController?.let {
                    val destination = if (it.currentDestination?.id == R.id.navigation_home){
                        R.id.action_navigation_home_to_navigation_cart
                    }else {
                        R.id.action_navigation_profile_to_navigation_cart
                    }
                    it.navigate(destination)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCartBadge(count: Int) {
      /*  if (count > 0) {
            cartBadge.text = count.toString()
            cartBadge.visibility = View.VISIBLE
        } else {
            cartBadge.visibility = View.GONE
        }*/
    }
}


