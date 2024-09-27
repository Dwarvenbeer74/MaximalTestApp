package com.example.maximaltestapp

import com.example.maximaltestapp.models.FollowerResponse
import com.example.maximaltestapp.models.GitResponse
import com.example.maximaltestapp.models.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun findUsers(
        @Query("q") user: String
    ): Call<GitResponse>

    @GET("users/{user}/followers")
    fun getFollowers(
        @Path("user") user: String
    ): Call<List<FollowerResponse>>

    @GET("users/{user}/repos")
    fun getRepos(
        @Path("user") user: String?
    ): Call<List<RepoResponse>>
}