package com.example.newzap.presentation.for_you.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return formatter.format(date)
}