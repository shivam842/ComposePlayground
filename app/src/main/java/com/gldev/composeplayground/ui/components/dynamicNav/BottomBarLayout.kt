package com.gldev.composeplayground.ui.components.dynamicNav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

@Composable
fun BottomBarLayout(
    destination: NavDestination,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    Scaffold {
        content(it)
    }
}

@Composable
fun NavigationRailLayout(
    destination: NavDestination,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        content()
    }
}