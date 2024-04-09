package com.alfadroid.githubusers.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubInterface {
    @GET("/users")
    fun requestUsers(): Call<ResponseBody>

    @GET("/users/{alias}")
    fun requestUserByAlias(@Path("alias") alias: String?): Call<ResponseBody>
}
