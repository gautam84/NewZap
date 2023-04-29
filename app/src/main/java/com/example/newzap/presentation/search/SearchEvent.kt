package com.example.newzap.presentation.search

import android.content.Context
import com.example.newzap.domain.model.News

sealed class SearchEvent {
    data class ChangeText(val text: String) : SearchEvent()
    data class Share(val news: News, val context: Context) : SearchEvent()
    data class Save(val news: News) : SearchEvent()
}
