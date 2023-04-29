package com.example.newzap.data.data_source.remote

import com.example.newzap.data.data_source.remote.dto.NewsResponse
import com.example.newzap.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = Constants.country,
        @Query("apiKey") apiKey: String? = Constants.API_KEY
    ): Response<NewsResponse?>

    @GET("v2/everything")
    suspend fun getNewsByKeyword(
        @Query("q") keyword: String,
        @Query("apiKey") apiKey: String =Constants.API_KEY
    ): Response<NewsResponse?>


}