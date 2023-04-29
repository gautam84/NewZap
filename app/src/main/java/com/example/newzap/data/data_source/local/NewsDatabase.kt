package com.example.newzap.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newzap.data.data_source.remote.dto.NewsDTO
import com.example.newzap.domain.model.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
    companion object{
        const val DATABASE_NAME = "news_db"
    }
}