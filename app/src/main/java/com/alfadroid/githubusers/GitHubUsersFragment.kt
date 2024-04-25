package com.alfadroid.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alfadroid.githubusers.databinding.FragmentGitHubUsersBinding
import com.alfadroid.githubusers.retrofit.RetrofitInstance
import com.alfadroid.githubusers.retrofit.UserByAliasDto
import com.alfadroid.githubusers.retrofit.UserInListDto
import com.google.gson.Gson


class GitHubUsersFragment : Fragment() {

    private lateinit var binding: FragmentGitHubUsersBinding
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGitHubUsersBinding.inflate(layoutInflater)

        setupRecyclerView()
        getListGitHubUsers()
        setupSwipeRefreshLayout()

        return binding.root
    }

    private fun getListGitHubUsers() {
        RetrofitInstance
            .retrofitService
            .requestUsers()
            .enqueue(object : retrofit2.Callback<List<UserInListDto>> {

                override fun onResponse(
                    call: retrofit2.Call<List<UserInListDto>>,
                    response: retrofit2.Response<List<UserInListDto>>
                ) {
                    val usersListGson = response.body()
                    usersListGson?.let { usersAdapter.submitList(it) }
                }

                override fun onFailure(call: retrofit2.Call<List<UserInListDto>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            getListGitHubUsers()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getSpecificUserByAlias(alias: String = "defunkt") {
        RetrofitInstance
            .retrofitService.requestUserByAlias(alias)
            .enqueue(object : retrofit2.Callback<UserByAliasDto> {

                override fun onResponse(
                    call: retrofit2.Call<UserByAliasDto>,
                    response: retrofit2.Response<UserByAliasDto>
                ) {
                    val userDataResponse = response.body()
                    val userDataGson = Gson().toJson(userDataResponse)
                }

                override fun onFailure(call: retrofit2.Call<UserByAliasDto>, t: Throwable) {
                }
            })
    }

    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter()
        binding.recyclerViewUsersFragment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
    }

}