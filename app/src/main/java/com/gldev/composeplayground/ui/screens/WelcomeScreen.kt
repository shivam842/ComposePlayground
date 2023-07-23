package com.gldev.composeplayground.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gldev.composeplayground.R

@Composable
fun WelcomeScreen() {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(64.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.match_day), contentDescription = "")
        Text(text = "This is title text.")
        Button(onClick = {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Next")
        }
    }
}

@Composable
fun WelcomeScreenFoldable(widthSizeClass: WindowWidthSizeClass) {
    val context = LocalContext.current

    if (widthSizeClass == WindowWidthSizeClass.Expanded) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.match_day),
                contentDescription = ""
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "This is title text.")
                Button(onClick = {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Next")
                }
            }
        }
    } else {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.match_day),
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
            Text(text = "This is title text.")
            Button(onClick = {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Next")
            }
        }
    }
}