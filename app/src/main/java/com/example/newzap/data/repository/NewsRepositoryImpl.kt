package com.example.newzap.data.repository

import com.example.newzap.data.data_source.local.NewsDao
import com.example.newzap.data.data_source.remote.NewsAPI
import com.example.newzap.domain.model.News
import com.example.newzap.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI,
    private val dao: NewsDao
) : NewsRepository {

    override suspend fun getHeadlines(): Flow<List<News>> = flow {

        if (newsAPI.getHeadlines().isSuccessful) {
            newsAPI.getHeadlines().body()?.newsList?.let {
                dao.getNews().collect { dbList ->

                    emit(it.map { newsDTO ->
                        News(
                            newsTitle = newsDTO.newsTitle,
                            newsDescription = newsDTO.newsDescription,
                            newsUrl = newsDTO.newsUrl,
                            newsImage = newsDTO.newsImage,
                            newsPublishedDate = newsDTO.newsPublishedDate,
                            isSaved = dbList.any { news -> news.newsTitle == newsDTO.newsTitle }
                        )
                    })
                }
            }
        } else {
            emit(emptyList())
        }


    }

    override suspend fun getPopularHeadlines(): Flow<List<News>> = flow {
        val response = newsAPI.getNewsByKeyword(keyword = "popular")

        if (response.isSuccessful) {
            response.body()?.newsList?.let {
                dao.getNews().collect { dbList ->

                    emit(it.map { newsDTO ->
                        News(
                            newsTitle = newsDTO.newsTitle,
                            newsDescription = newsDTO.newsDescription,
                            newsUrl = newsDTO.newsUrl,
                            newsImage = newsDTO.newsImage,
                            newsPublishedDate = newsDTO.newsPublishedDate,
                            isSaved = dbList.any { news -> news.newsTitle == newsDTO.newsTitle }
                        )
                    })
                }

            }

        } else {
            emit(emptyList())
        }
    }


    override suspend fun getSavedNews(): Flow<List<News>> {
        return dao.getNews()

    }

    override suspend fun saveNews(news: News) {
        dao.insert(news)

    }

    override suspend fun deleteNews(news: News) {
        dao.delete(news)

    }

    override suspend fun deleteByTitle(news: News) {
        dao.deleteByTitle(news.newsTitle)
    }

    override suspend fun getNewsFromKeyword(keyword: String): Flow<List<News>> = flow {
        val response = newsAPI.getNewsByKeyword(keyword = keyword)

        if (response.isSuccessful) {
            response.body()?.newsList?.let {
                dao.getNews().collect { dbList ->

                    emit(it.map { newsDTO ->
                        News(
                            newsTitle = newsDTO.newsTitle,
                            newsDescription = newsDTO.newsDescription,
                            newsUrl = newsDTO.newsUrl,
                            newsImage = newsDTO.newsImage,
                            newsPublishedDate = newsDTO.newsPublishedDate,
                            isSaved = dbList.any { news -> news.newsTitle == newsDTO.newsTitle }
                        )
                    })
                }

            }

        } else {
            emit(emptyList())
        }
    }


}

