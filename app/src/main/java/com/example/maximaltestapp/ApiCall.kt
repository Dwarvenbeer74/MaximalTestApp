package com.example.maximaltestapp

import android.content.Context
import android.util.Log
import com.example.maximaltestapp.models.FollowerResponse
import com.example.maximaltestapp.models.GitResponse
import com.example.maximaltestapp.models.RepoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiCall {

    fun findUsers(context: Context, user: String, callback: (GitResponse) -> Unit) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        val call: Call<GitResponse> = service.findUsers(user)

        call.enqueue(object : Callback<GitResponse> {

            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                if(response.isSuccessful){
                    val users: GitResponse = response.body() as GitResponse
                    callback(users)
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                Log.e("qwerty", "response. onFailure=${t}")
            }
        })
    }

    fun findFollowers(user: String, callback: (List<FollowerResponse>) -> Unit) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        val call: Call<List<FollowerResponse>> = service.getFollowers(user)

        call.enqueue(object : Callback<List<FollowerResponse>> {

            override fun onResponse(call: Call<List<FollowerResponse>>, response: Response<List<FollowerResponse>>) {
                if(response.isSuccessful){
                    val users: List<FollowerResponse> = response.body() as List<FollowerResponse>
                    callback(users)
                }
            }

            override fun onFailure(call: Call<List<FollowerResponse>>, t: Throwable) {
                Log.e("qwerty", "response. onFailure=${t}")
            }
        })
    }

    fun getRepos(user: String?, callback: (List<RepoResponse>) -> Unit) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        val call: Call<List<RepoResponse>> = service.getRepos(user)

        call.enqueue(object : Callback<List<RepoResponse>> {

            override fun onResponse(call: Call<List<RepoResponse>>, response: Response<List<RepoResponse>>) {
                if(response.isSuccessful){
                    val repos: List<RepoResponse> = response.body() as List<RepoResponse>
                    callback(repos)
                }
            }

            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                Log.e("qwerty", "response. onFailure=${t}")
            }
        })
    }
}