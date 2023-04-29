package com.example.newzap.presentation.sections_detail

import com.example.newzap.domain.model.News

data class SectionDetailState(
    val sectionList: List<News> = mutableListOf()

)
