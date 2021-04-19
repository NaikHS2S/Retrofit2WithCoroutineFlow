package com.example.dev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dev.R
import com.example.dev.databinding.ActivityMainBinding
import com.example.dev.ui.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
                .commit()
    }
}