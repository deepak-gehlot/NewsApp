package com.news.newsapp.api

import com.news.newsapp.api.response.Response

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines?apiKey=5c4ad3fcc8b14e6b9b4e6c312af065fc")
    fun getTopHeadLines(@Query("country") country: String,
                        @Query("category") category: String): Call<Response>

    companion object {

        val BASE_URL = "https://newsapi.org/v2/"
        val SUCCESS = "200"
    }
}
