package com.alfadroid.githubusers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfadroid.githubusers.databinding.FragmentGitHubUserBinding
import com.alfadroid.githubusers.retrofit.RetrofitInstance
import com.alfadroid.githubusers.retrofit.UserByAliasDto
import com.bumptech.glide.Glide

class GitHubUserFragment() : Fragment() {
    private lateinit var userLogin: String
    private lateinit var binding: FragmentGitHubUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userLogin = arguments?.getString("userLogin") ?: "defunkt"
        getSpecificUserByAlias(userLogin)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGitHubUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getSpecificUserByAlias(alias: String = "defunkt") {
        RetrofitInstance
            .retrofitService.requestUserByAlias(alias)
            .enqueue(object : retrofit2.Callback<UserByAliasDto> {

                override fun onResponse(
                    call: retrofit2.Call<UserByAliasDto>,
                    response: retrofit2.Response<UserByAliasDto>
                ) {
                    val userByAliasDto = response.body()
                    with(binding) {
                        tvUserName.text = userByAliasDto?.login
                        tvUserEmail.text = userByAliasDto?.email ?: "-"
                        tvFollowingCount.text = userByAliasDto?.following.toString() ?: "-"
                        tvFollowersCount.text = userByAliasDto?.followers.toString() ?: "-"
                    }
                    Glide.with(requireActivity())
                        .load(userByAliasDto?.avatarUrl)
                        .into(binding.ivUserAvatar)
                }

                override fun onFailure(call: retrofit2.Call<UserByAliasDto>, t: Throwable) {
                }
            })
    }

    companion object {
        fun newInstance(
            userLogin: String
        ) =
            GitHubUserFragment().apply {
                arguments = Bundle().apply {
                    putString("userLogin", userLogin)
                }
            }
    }
}