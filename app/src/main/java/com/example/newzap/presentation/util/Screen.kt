package com.example.newzap.presentation.util

sealed class Screen(val route: String) {
    object ForYouScreen : Screen(route = "for_you_screen")
    object HomeScreen: Screen(route = "home_screen")
    object SearchScreen: Screen(route="search_screen")
    object SectionsScreen: Screen(route="sections_screen")
    object SettingsScreen: Screen(route="settings_screen")
    object SectionsDetailScreen: Screen(route = "sections_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
