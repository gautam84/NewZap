package com.example.newzap.presentation.common.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newzap.R
import com.example.newzap.domain.model.News
import com.example.newzap.presentation.for_you.util.formatDate

@Composable
fun SmallNewsCard(
    news: News, onDeleteClicked: (news: News) -> Unit, onShareClicked: (news: News) -> Unit
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(news.newsUrl)) }

    Row(modifier = Modifier
        .clickable {
            context.startActivity(intent)
        }
        .fillMaxWidth()
        .padding(16.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(
                news.newsImage ?: R.drawable.container
            ).build(),
            contentDescription = news.newsTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp),
            placeholder = painterResource(id = R.drawable.container)
        )
        Column(
            modifier = Modifier
                .height(120.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = news.newsTitle,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = formatDate(news.newsPublishedDate))
            Row(
                modifier = Modifier.fillMaxWidth()


            ) {
                Icon(imageVector = Icons.Filled.Send,
                    contentDescription = "Share",
                    modifier = Modifier.clickable {
                        onShareClicked(news)

                    })

                Icon(
                    imageVector = if (news.isSaved) {
                    Icons.Outlined.Bookmark
                } else {
                    Icons.Outlined.BookmarkBorder
                },
                    contentDescription = "Bookmark", modifier = Modifier.clickable {
                    onDeleteClicked(news)
                })
            }


        }

    }
}