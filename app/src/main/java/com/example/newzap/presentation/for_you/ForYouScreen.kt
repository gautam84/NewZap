package com.example.newzap.presentation.for_you

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newzap.presentation.common.components.ScreenCard
import com.example.newzap.presentation.common.components.SmallNewsCard

@Composable
fun ForYouScreen(
    viewModel: ForYouViewModel = hiltViewModel()
) {
    val savedList = viewModel.forYouState.value.savedList
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenCard(title = "My News")

        if (savedList.isNotEmpty()) {
            LazyColumn() {
                items(savedList.size) {
                    SmallNewsCard(savedList[it], onShareClicked = { news ->
                        viewModel.onEvent(ForYouEvent.Share(news, context))
                    }, onDeleteClicked = { news ->
                        viewModel.onEvent(ForYouEvent.Delete(news))
                    })
                }
                item {
                    Spacer(modifier = Modifier.height(48.dp))
                }

            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Empty", color = Color.Black.copy(0.5f))

            }
        }
    }
}



