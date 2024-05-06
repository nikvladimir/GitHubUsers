package com.alfadroid.githubusers.retrofit

import com.alfadroid.githubusers.Constants.Companion.URL_GITHUB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_GITHUB)
        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: GitHubInterface = retrofit.create(GitHubInterface::class.java)

    private fun httpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}