package com.example.newzap.presentation.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newzap.presentation.common.components.SmallNewsCard

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()

) {
    val state = viewModel.searchScreenState.value
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar(
            state = state.text,
            event = { viewModel.onEvent(SearchEvent.ChangeText(it)) }

        )

        LazyColumn() {
            items(state.searchedList.size) {
                SmallNewsCard(state.searchedList[it], onShareClicked = { news ->
                    viewModel.onEvent(SearchEvent.Share(news, context))
                }, onDeleteClicked = { news ->
                    viewModel.onEvent(SearchEvent.Save(news))
                })
            }
            item {
                Spacer(modifier = Modifier.height(48.dp))
            }

        }
    }
}

@Composable
fun SearchBar(
    state: String, event: (text: String) -> Unit
) {

    TextField(
        value = state,
        onValueChange = { event(it) },
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Search",
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .border(width = 1.dp, Color.Black, shape = CircleShape),

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.White,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}