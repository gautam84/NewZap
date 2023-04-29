package com.example.newzap.di

import android.app.Application
import androidx.room.Room
import com.example.newzap.data.data_source.local.NewsDatabase
import com.example.newzap.data.data_source.remote.NewsAPI
import com.example.newzap.data.repository.NewsRepositoryImpl
import com.example.newzap.domain.repository.NewsRepository
import com.example.newzap.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesNewsAPI(): NewsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.base_url)
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsAPI: NewsAPI, db: NewsDatabase
    ): NewsRepository = NewsRepositoryImpl(newsAPI, db.newsDao)

    @Provides
    @Singleton
    fun providesNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            NewsDatabase.DATABASE_NAME
        ).build()

    }




}