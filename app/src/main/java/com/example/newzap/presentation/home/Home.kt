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


package com.example.newzap.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newzap.R
import com.example.newzap.domain.model.News
import com.example.newzap.presentation.util.Screen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(
    navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val homeScreenState = viewModel.homeScreenState
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(40.dp)

                )
            }
            IconButton(onClick = {
                navHostController.navigate(Screen.SettingsScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings",
                    tint = Color.Black
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Tabs(pagerState = pagerState)
            TabsContent(pagerState = pagerState,
                headlinesList = homeScreenState.value.headlinesList,
                popularList = homeScreenState.value.popularList,
                onShareClicked = {
                    viewModel.onEvent(HomeEvent.Share(it, context))
                },
                onSavedClicked = {
                    viewModel.onEvent(HomeEvent.Save(it))
                })

        }

    }

}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("News", "Popular")
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions), color = Color.Black
            )
        },
        divider = {
            Divider(Modifier.fillMaxWidth())

        }

    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        fontSize = 16.sp,
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Light,
                        color = Color.Black,
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@Composable
fun NewsSection(
    headlinesList: List<News>,
    onShareClicked: (news: News) -> Unit,
    onSavedClicked: (news: News) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (headlinesList.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(
                    bottom = 56.dp
                )
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(
                            top = 8.dp,
                            end = 16.dp,
                            start = 16.dp,
                        )
                    ) {
                        Text(text = "LATEST NEWS", style = MaterialTheme.typography.caption)
                    }
                }
                items(headlinesList.size) {
                    NewsCard(
                        news = headlinesList[it],
                        onShareClicked = onShareClicked,
                        onSavedClicked = onSavedClicked
                    )
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()

            }
        }


    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    headlinesList: List<News>,
    popularList: List<News>,
    onShareClicked: (news: News) -> Unit,
    onSavedClicked: (news: News) -> Unit,

    ) {

    HorizontalPager(count = 2, state = pagerState) { page ->
        when (page) {
            0 -> NewsSection(
                headlinesList = headlinesList,
                onSavedClicked = onSavedClicked,
                onShareClicked = onShareClicked
            )

            1 -> PopularSection(
                popularList = popularList,
                onSavedClicked = onSavedClicked,
                onShareClicked = onShareClicked
            )
        }
    }

}

@Composable
fun PopularSection(
    popularList: List<News>,
    onShareClicked: (news: News) -> Unit,
    onSavedClicked: (news: News) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (popularList.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(
                    bottom = 56.dp
                )
            ) {
                items(popularList.size) {
                    NewsCard(
                        news = popularList[it],
                        onShareClicked = onShareClicked,
                        onSavedClicked = onSavedClicked
                    )
                }

            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()

            }
        }

    }
}

@Composable
fun NewsCard(
    news: News,
    onShareClicked: (news: News) -> Unit,
    onSavedClicked: (news: News) -> Unit
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(news.newsUrl)) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            context.startActivity(intent)
        }
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = news.newsTitle)
        Spacer(modifier = Modifier.height(12.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(
                news.newsImage ?: R.drawable.container
            ).build(),
            contentDescription = news.newsTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            placeholder = painterResource(id = R.drawable.container)
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = news.newsDescription ?: "Description not available.",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.weight(0.8f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                horizontalArrangement = Arrangement.End

            ) {
                Icon(imageVector =
                Icons.Filled.Send, contentDescription = "Share", modifier = Modifier.clickable {
                    onShareClicked(news)
                })

                Icon(imageVector = if (news.isSaved) {
                    Icons.Outlined.Bookmark
                } else {
                    Icons.Outlined.BookmarkBorder
                },
                    contentDescription = "Bookmark",
                    modifier = Modifier.clickable {
                        onSavedClicked(news)
                    })

            }
        }

    }
}
