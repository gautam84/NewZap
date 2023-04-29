package com.example.newzap.presentation.for_you

import android.content.Context
import com.example.newzap.domain.model.News

sealed class ForYouEvent {
    data class Share(val news: News, val context: Context) : ForYouEvent()
    data class Delete(val news: News) : ForYouEvent()
}

