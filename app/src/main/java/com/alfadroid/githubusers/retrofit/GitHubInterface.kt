package com.alfadroid.githubusers.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubInterface {
    @GET("/users")
    fun requestUsers(): Call<List<UserInListDto>>

    @GET("/users/{alias}")
    fun requestUserByAlias(
        @Path("alias") alias: String?
    ): Call<UserByAliasDto>
}
