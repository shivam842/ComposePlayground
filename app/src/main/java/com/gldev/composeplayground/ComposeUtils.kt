package com.gldev.composeplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun getScreenWidthDp(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
fun getScreenHeightDp(): Dp = LocalConfiguration.current.screenHeightDp.dp

@Composable
fun getScreenWidthPx(): Float = LocalDensity.current.run { getScreenWidthDp().toPx() }

@Composable
fun getScreenHeightPx(): Float = LocalDensity.current.run { getScreenHeightDp().toPx() }
