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
import com.alfadroid.githubusers.retrofit.UserInListDto


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

    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter { userLogin ->
            openFragment(userLogin)
        }
        binding.recyclerViewUsersFragment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
    }

    private fun openFragment(userLogin: String) {
        val gitHubUserFragment = GitHubUserFragment.newInstance(userLogin = userLogin)

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, gitHubUserFragment)
            addToBackStack("GitHubUserFragment")
            commit()
        }
    }

}