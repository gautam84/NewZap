package com.example.newzap.presentation.search

import com.example.newzap.domain.model.News

data class SearchState(
    val text: String = "",
    val searchedList: List<News> = mutableListOf()
)
