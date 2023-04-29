package com.example.newzap.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    var status: String = String(),

    @SerializedName("totalResults")
    val totalNewsCount: Int = 0,

    @SerializedName("articles")
    var newsList: List<NewsDTO> = ArrayList()
)
