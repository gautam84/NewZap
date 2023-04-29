package com.example.newzap.presentation.search

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzap.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {
    private val _searchScreenState = mutableStateOf(SearchState())
    val searchScreenState: State<SearchState> = _searchScreenState

    init {



    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ChangeText -> {
                _searchScreenState.value = searchScreenState.value.copy(
                   text = event.text
                )
                viewModelScope.launch {
                    repository.getNewsFromKeyword(_searchScreenState.value.text).collect {
                        _searchScreenState.value = searchScreenState.value.copy(
                            searchedList = it
                        )
                    }
                }
            }

            is SearchEvent.Share -> {
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

            is SearchEvent.Save -> {
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