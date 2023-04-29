package com.example.newzap.presentation.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newzap.presentation.common.components.PressCard
import com.example.newzap.presentation.common.components.ScreenCard

@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val privacyPolicy =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org/privacy")) }
    val termsOfSService =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org/terms")) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            ScreenCard(title = "Settings")
            Column {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        PressCard(name = "Terms of Service") {
            context.startActivity(termsOfSService)
        }

        PressCard(name = "Privacy Policy") {
            context.startActivity(privacyPolicy)

        }

    }
}