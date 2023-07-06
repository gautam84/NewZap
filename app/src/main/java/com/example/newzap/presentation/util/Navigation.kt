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

package com.example.newzap.presentation.util

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newzap.presentation.for_you.ForYouScreen
import com.example.newzap.presentation.home.Home
import com.example.newzap.presentation.search.SearchScreen
import com.example.newzap.presentation.sections.Sections
import com.example.newzap.presentation.sections_detail.SectionsDetail
import com.example.newzap.presentation.settings.SettingsScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupNavigation() {

    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route




    Scaffold(
        bottomBar = {
            if ((currentRoute == Screen.HomeScreen.route) || (currentRoute == Screen.ForYouScreen.route) || (currentRoute == Screen.SearchScreen.route) || (currentRoute == Screen.SectionsScreen.route)) {
                BottomBar(navController = navController)
            }
        }
    ) {
        NavigationGraph(
            navController = navController,
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = Color(0xFF576F72)
    ) {
        BottomNavigationItem(
            icon =
            {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Screen",
                )
            },
            label = { Text(text = "Home") },

            selectedContentColor = Color(0xFFE4DCCF),
            unselectedContentColor = Color(0xFF7D9D9C),
            alwaysShowLabel = false,
            selected = currentRoute == Screen.HomeScreen.route,

            onClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        )


        BottomNavigationItem(
            icon =
            {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "For You Screen",
                )
            },
            label = { Text(text = "For You") },


            selectedContentColor = Color(0xFFE4DCCF),
            unselectedContentColor = Color(0xFF7D9D9C),
            alwaysShowLabel = false,
            selected = currentRoute == Screen.ForYouScreen.route,

            onClick = {
                navController.navigate(Screen.ForYouScreen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        )

        BottomNavigationItem(
            icon =
            {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Screen",
                )
            },

            label = { Text(text = "Search") },


            selectedContentColor = Color(0xFFE4DCCF),
            unselectedContentColor = Color(0xFF7D9D9C),
            alwaysShowLabel = false,
            selected = currentRoute == Screen.SearchScreen.route,

            onClick = {
                navController.navigate(Screen.SearchScreen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon =
            {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Sections Screen",
                )
            },

            label = { Text(text = "Sections") },


            selectedContentColor = Color(0xFFE4DCCF),
            unselectedContentColor = Color(0xFF7D9D9C),
            alwaysShowLabel = false,
            selected = currentRoute == Screen.SectionsScreen.route,

            onClick = {
                navController.navigate(Screen.SectionsScreen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        )

    }


}

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route,
        ) {
            Home(navController)
        }
        composable(
            route = Screen.ForYouScreen.route,
        ) {
            ForYouScreen()
        }
        composable(
            route = Screen.SectionsScreen.route,

            ) {

            Sections(navHostController = navController)
        }
        composable(
            route = Screen.SearchScreen.route,
        ) {
            SearchScreen()
        }
        composable(
            route = Screen.SettingsScreen.route,
        ) {
            SettingsScreen(navController)
        }
        composable(
            route = Screen.SectionsDetailScreen.route + "/{sectionName}",
            arguments = listOf(
                navArgument(
                    name = "sectionName"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val sectionName = it.arguments?.getString("sectionName") ?: ""

            SectionsDetail(sectionName, navController)
        }
    }
}