package com.alfadroid.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfadroid.githubusers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, GitHubUsersFragment())
            commit()
        }

    }
}