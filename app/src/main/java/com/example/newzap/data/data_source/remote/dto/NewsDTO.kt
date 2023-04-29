package com.example.newzap.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NewsDTO(


    @SerializedName("source")
    var source: SourceDTO = SourceDTO(),

    @SerializedName("title")
    val newsTitle: String = String(),

    @SerializedName("description")
    val newsDescription: String? = String(),

    @SerializedName("url")
    val newsUrl: String = String(),

    @SerializedName("urlToImage")
    val newsImage: String? = String(),

    @SerializedName("publishedAt")
    var newsPublishedDate: Date = Date()


)
