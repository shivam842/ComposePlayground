package com.gldev.composeplayground.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gldev.composeplayground.R

@Preview
@Composable
fun Toolbar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        //view Location
        ViewLocation()

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            //view wallet
            ViewWallet()

            Spacer(modifier = Modifier.width(12.dp))

            //view cart
            ViewCart()
        }
    }
}


@Composable
fun ViewCart() {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFB5D0F9),
                shape = RoundedCornerShape(size = 100.dp)
            )
            .width(36.dp)
            .height(36.dp)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {

        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_cart_shopping),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )

        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(14.dp)
                .height(14.dp),
            painter = painterResource(id = R.drawable.ic_ellipse_429),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
    }
}

@Composable
fun ViewWallet() {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFB5D0F9),
                shape = RoundedCornerShape(size = 100.dp)
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_frame),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
        /*Image(
            modifier = Modifier
                .padding(1.5.dp)
                .width(8.dp)
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_rupee),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )*/
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .fillMaxHeight()
        ) {
            Text(
                text = "\u20b9 200",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    /*fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_semi_bold)),*/
                    color = Color(0xFF2C3741),
                )
            )
        }
    }
}

@Composable
fun ViewLocation() {
    Row(
        modifier = Modifier
            .height(20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mumbai",
            modifier = Modifier.height(20.dp),
            style = TextStyle(
                fontSize = 16.sp,
                /*fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_medium)),
                fontWeight = FontWeight(500),*/
                color = Color(0xFF2C3741),
                letterSpacing = 0.2.sp,
            )
        )
        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(16.dp)
                .height(16.dp),
            painter = painterResource(id = R.drawable.ic_chevron_down),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
    }
}
