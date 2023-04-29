package com.example.newzap.presentation.for_you

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzap.domain.repository.NewsRepository
import com.example.newzap.presentation.home.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _forYouState = mutableStateOf(ForYouState())
    val forYouState: State<ForYouState> = _forYouState

    init {
        viewModelScope.launch {
            repository.getSavedNews().collect {
                _forYouState.value = forYouState.value.copy(
                    savedList = it
                )
            }
        }
    }

    fun onEvent(event: ForYouEvent) {

        when (event) {
            is ForYouEvent.Share -> {
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

            is ForYouEvent.Delete -> {
                viewModelScope.launch {
                    repository.deleteNews(event.news)
                }

            }

        }

    }


}