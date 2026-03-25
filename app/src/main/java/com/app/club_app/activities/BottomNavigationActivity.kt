package com.app.club_app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.club_app.R
import com.app.club_app.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        setUpBottomNavigation()
    }
    private  fun  setUpBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNav.setupWithNavController(navController)
    }
    private fun setStatusBar(){
        window.statusBarColor = getColor(R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
    }
}