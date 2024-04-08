package com.alfadroid.githubusers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfadroid.githubusers.databinding.FragmentGitHubUsersBinding

class GitHubUserFragment : Fragment() {

    private lateinit var binding: FragmentGitHubUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGitHubUsersBinding.inflate(layoutInflater)

        return binding.root
    }
}