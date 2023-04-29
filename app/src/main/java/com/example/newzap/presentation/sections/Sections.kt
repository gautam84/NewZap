package com.example.newzap.presentation.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newzap.presentation.common.components.PressCard
import com.example.newzap.presentation.common.components.ScreenCard
import com.example.newzap.presentation.util.Screen

@Composable
fun Sections(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenCard(title = "Sections")
        Spacer(modifier = Modifier.height(8.dp))
        val sectionList =
            listOf("Politics", "Business", "Education", "Arts", "Sports", "Science", "Health")
        LazyColumn() {
            items(sectionList.size) {
                PressCard(name = sectionList[it], onClick = {
                    navHostController.navigate(Screen.SectionsDetailScreen.withArgs(sectionList[it]))
                })
            }
        }

    }
}

