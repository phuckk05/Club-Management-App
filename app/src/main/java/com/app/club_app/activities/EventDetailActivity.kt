package com.app.club_app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.club_app.R
import com.app.club_app.databinding.ActivityEventDetailBinding

class EventDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}