package com.example.newzap.presentation.home

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzap.domain.model.News
import com.example.newzap.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _homeScreenState = mutableStateOf(HomeState())
    val homeScreenState: State<HomeState> = _homeScreenState

    init {
        viewModelScope.launch {

            repository.getHeadlines().collect {
                _homeScreenState.value = homeScreenState.value.copy(
                    headlinesList = it.toMutableList()
                )
            }






        }
        viewModelScope.launch {
            repository.getPopularHeadlines().collect {
                _homeScreenState.value = homeScreenState.value.copy(
                    popularList = it.toMutableList()
                )
            }
        }
    }


    fun onEvent(event: HomeEvent) {

        when (event) {
            is HomeEvent.Share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${event.news.newsTitle}:\n${event.news.newsUrl}\n\nKeep yourself updated with NewZap created by Gautam Hazarika."
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                event.context.startActivity(shareIntent)
            }

            is HomeEvent.Save -> {
                viewModelScope.launch {
                    if (event.news.isSaved) {
                    repository.deleteByTitle(event.news.copy(
                        isSaved = false
                    ))
                    } else{
                        repository.saveNews(event.news.copy(
                            isSaved = true
                        ))
                    }

                }

            }

        }

    }



}