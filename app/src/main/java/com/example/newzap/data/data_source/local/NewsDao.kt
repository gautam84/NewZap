package com.example.newzap.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newzap.domain.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {


    @Query("SELECT * FROM news_table")
    fun getNews(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("DELETE FROM news_table WHERE newsTitle = :title")
    suspend fun deleteByTitle(title: String)


}
