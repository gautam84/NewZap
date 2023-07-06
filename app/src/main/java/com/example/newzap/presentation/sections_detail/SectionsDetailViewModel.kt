/**
 *
 *	MIT License
 *
 *	Copyright (c) 2023 Gautam Hazarika
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 **/


package com.example.newzap.presentation.sections_detail

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzap.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionsDetailViewModel @Inject constructor(
    private val repository: NewsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val sectionName: String = checkNotNull(savedStateHandle["sectionName"])

    private val _sectionDetailState = mutableStateOf(SectionDetailState())
    val sectionDetailState: State<SectionDetailState> = _sectionDetailState


    init {
        viewModelScope.launch {
            repository.getNewsFromKeyword(sectionName).collect {
                _sectionDetailState.value = sectionDetailState.value.copy(
                    sectionList = it
                )
            }
        }
    }

    fun onEvent(event: SectionDetailEvent) {
        when (event) {
            is SectionDetailEvent.Share -> {
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

            is SectionDetailEvent.Save -> {
                viewModelScope.launch {
                    if (event.news.isSaved) {
                        repository.deleteByTitle(
                            event.news.copy(
                                isSaved = false
                            )
                        )
                    } else {
                        repository.saveNews(
                            event.news.copy(
                                isSaved = true
                            )
                        )
                    }

                }

            }
        }
    }

}