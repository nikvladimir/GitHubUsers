package com.alfadroid.githubusers.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface GitHubInterface {
    @GET("/users")
    fun requestUsers(): Call<ResponseBody>
}
