package com.alfadroid.githubusers.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubInterface {
    @GET("/users")
    suspend fun requestUsers(): Response<List<UserInListDto>>

    @GET("/users/{alias}")
    suspend fun requestUserByAlias(
        @Path("alias") alias: String?
    ): Response<UserByAliasDto>
}
