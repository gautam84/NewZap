/**
 *
 *	MIT License
 *
 *	Copyright (c) 2023 Gautam Hazarika
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 **/

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

