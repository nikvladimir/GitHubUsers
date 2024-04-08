package com.alfadroid.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.githubusers.databinding.FragmentGitHubUsersBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


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

        val URL = "https://api.github.com/users"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(URL).build()
        val callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Запрос не выполнился")
            }

            override fun onResponse(call: Call, response: Response) {
                println("Запрос выполнился УСПЕШНО ${response.body?.string()}")
            }

        }

        okHttpClient.newCall(request).enqueue(callback)

        return binging.root
    }
}