package com.gldev.composeplayground.ui.components.navigaation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gldev.composeplayground.ui.screens.ActivityScreen
import com.gldev.composeplayground.ui.screens.HomeScreen
import com.gldev.composeplayground.ui.screens.ProfileScreen
import com.gldev.composeplayground.ui.screens.SearchScreen
import com.gldev.composeplayground.ui.screens.WriteScreen

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomBarScreen.Search.route) {
            SearchScreen(navController)
        }
        composable(BottomBarScreen.Write.route) {
            WriteScreen(navController)
        }
        composable(BottomBarScreen.Activity.route) {
            ActivityScreen(navController)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}