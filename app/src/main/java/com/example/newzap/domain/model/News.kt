package com.example.newzap.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val newsTitle: String,
    val newsDescription: String?,
    val newsUrl: String,
    val newsImage: String?,
    val newsPublishedDate: Date,
    val isSaved: Boolean
)