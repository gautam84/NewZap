package com.example.newzap.presentation.sections_detail

import android.content.Context
import com.example.newzap.domain.model.News

sealed class SectionDetailEvent{
    data class Share(val news: News, val context: Context) : SectionDetailEvent()
    data class Save(val news: News) : SectionDetailEvent()
}
