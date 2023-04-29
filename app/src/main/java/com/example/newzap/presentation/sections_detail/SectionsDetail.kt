package com.example.newzap.presentation.sections_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newzap.presentation.common.components.ScreenCard
import com.example.newzap.presentation.common.components.SmallNewsCard

@Composable
fun SectionsDetail(
    sectionName: String,
    navHostController: NavHostController,
    viewModel: SectionsDetailViewModel = hiltViewModel()

) {
    val state = viewModel.sectionDetailState.value.sectionList
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            ScreenCard(title = sectionName)
            Column {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }

        if (state.isNotEmpty()) {
            LazyColumn() {
                items(state.size) {
                    SmallNewsCard(state[it], onShareClicked = { news ->
                        viewModel.onEvent(SectionDetailEvent.Share(news, context))
                    }, onDeleteClicked = { news ->
                        viewModel.onEvent(SectionDetailEvent.Save(news))
                    })
                }


            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()

            }
        }


    }
}