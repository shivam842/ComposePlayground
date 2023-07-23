package com.gldev.composeplayground.ui.components.dynamicNav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gldev.composeplayground.ui.screens.ActivityScreen
import com.gldev.composeplayground.ui.screens.ProfileScreen

@Composable
fun DynamicNav(windowSizeClass: WindowSizeClass) {

    val navHostController = rememberNavController()

    val navHost = remember {
        movableContentOf<PaddingValues> {

            NavHost(navHostController, "profile") {
                composable("profile") { ProfileScreen(navHostController) }
                composable("friendslist") { ActivityScreen(navHostController) }
            }
        }
    }

    // Perform logic on the size class to decide whether to show
    // the top app bar.
    val showBottomBar = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    if (showBottomBar) {
        BottomBarLayout(
            NavDestination(""),
            onMenuItemSelected = {}
        ) { paddingValues ->
            navHost(paddingValues)
        }
    } else {
        NavigationRailLayout(
            NavDestination(""),
            onMenuItemSelected = {}
        ) {

        }
    }
}