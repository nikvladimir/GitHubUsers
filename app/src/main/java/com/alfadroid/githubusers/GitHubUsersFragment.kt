package com.alfadroid.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.githubusers.databinding.FragmentGitHubUsersBinding
import com.alfadroid.githubusers.retrofit.RetrofitInstance
import com.alfadroid.githubusers.retrofit.UserByAliasDto
import com.alfadroid.githubusers.retrofit.UserInListDto
import com.google.gson.Gson


class GitHubUsersFragment : Fragment() {

    private lateinit var binging: FragmentGitHubUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binging = FragmentGitHubUsersBinding.inflate(layoutInflater)

        getListGinHubUsers()
        getSpecificUserByAlias()

        return binging.root
    }

    private fun getListGinHubUsers() {
        RetrofitInstance
            .retrofitService
            .requestUsers()
            .enqueue(object : retrofit2.Callback<List<UserInListDto>> {

                override fun onResponse(
                    call: retrofit2.Call<List<UserInListDto>>,
                    response: retrofit2.Response<List<UserInListDto>>
                ) {
                    val usersListResponse = response.body()
                    val usersListGson = Gson().toJson(usersListResponse)
                    println("USERS DATA Gson: $usersListGson")
                }

                override fun onFailure(call: retrofit2.Call<List<UserInListDto>>, t: Throwable) {
                    println(">>>>>>>>    Запрос requestUsers не выполнился")
                    t.printStackTrace()
                }
            })
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
                    println("CURRENT USER Gson: $userDataGson")
                }

                override fun onFailure(call: retrofit2.Call<UserByAliasDto>, t: Throwable) {
                    println(">>>>>>>>    Запрос requestUserByAlias не выполнился")
                }
            })

    }
}