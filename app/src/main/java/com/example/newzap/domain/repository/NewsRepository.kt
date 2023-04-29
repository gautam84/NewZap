package com.example.newzap.domain.repository

import com.example.newzap.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getHeadlines(): Flow<List<News>>
    suspend fun getPopularHeadlines(): Flow<List<News>>
    suspend fun getSavedNews(): Flow<List<News>>
    suspend fun saveNews(news: News)
    suspend fun deleteNews(news: News)
    suspend fun deleteByTitle(news: News)
    suspend fun getNewsFromKeyword(keyword: String): Flow<List<News>>
}