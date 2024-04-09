package com.alfadroid.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.githubusers.databinding.FragmentGitHubUsersBinding
import com.alfadroid.githubusers.retrofit.GitHubInterface
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit


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

        val baseURL = "https://api.github.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(OkHttpClient())
            .build()

        val retrofitService = retrofit.create(GitHubInterface::class.java)
        retrofitService.requestUsers().enqueue(object : retrofit2.Callback<ResponseBody> {

            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                println("Запрос выполнился УСПЕШНО ${response.body()?.string()}")
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                println("Запрос не выполнился")
            }
        })

        return binging.root
    }
}