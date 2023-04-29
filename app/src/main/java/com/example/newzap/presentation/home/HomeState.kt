package com.example.newzap.presentation.home

import com.example.newzap.domain.model.News

data class HomeState(
    val headlinesList: MutableList<News> = mutableListOf(),
    val popularList: MutableList<News> = mutableListOf(),

    )
