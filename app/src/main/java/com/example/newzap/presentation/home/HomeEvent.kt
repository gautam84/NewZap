package com.example.newzap.presentation.home

import android.content.Context
import com.example.newzap.data.data_source.remote.dto.NewsDTO
import com.example.newzap.domain.model.News

sealed class HomeEvent {
    data class Share(val news: News, val context: Context) : HomeEvent()
    data class Save(val news: News) : HomeEvent()
}