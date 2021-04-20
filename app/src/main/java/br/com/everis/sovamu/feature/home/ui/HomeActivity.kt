package br.com.everis.sovamu.feature.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.everis.sovamu.R
import br.com.everis.sovamu.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment)
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            binding.homeBottomNavigation.setupWithNavController(navController)
        }
    }
}