package com.gldev.composeplayground.ui.homepage

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.gldev.composeplayground.ui.components.GifImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(56.dp)
            .background(color = Color(0xFFFBFDFF))
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar()
        HomeContent()
    }
}

@Composable
fun ViewSearch() {
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
    Row(
        modifier = Modifier
            .drawColoredShadow(
                color = Color(0xFFDAE9FF),
                borderRadius = 16.dp,
                offsetY = 1.dp,
                spread = 2.dp
            )
            .border(
                width = 1.dp, color = Color(0xFFBFD9FF), shape = RoundedCornerShape(size = 16.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .width(288.dp)
                .height(24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .padding(1.dp)
                    .width(20.dp)
                    .height(20.dp),
                painter = painterResource(id = R.drawable.search_alt),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Text(
                modifier = Modifier.height(24.dp), text = "Search for medicine", style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF808080),
                )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPromo(
    pageCount: Int = 10,
    pagerState: PagerState = rememberPagerState(0),
    autoScrollDuration: Long = 3000L
) {

    Spacer(modifier = Modifier.height(8.dp))

    //View HorizontalView
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
        if (isDragged.not()) {
            with(pagerState) {
                var currentPageKey by remember { mutableStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
            pageCount = pageCount,
            contentPadding = PaddingValues(horizontal = 18.dp)
        ) { page ->
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_banner_minimal),
                contentDescription = " "
            )
        }
    }

    //View Indicator
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        items(count = 10) { index ->

            val sWidth = if (index == pagerState.currentPage) 20.dp else 12.dp
            val color =
                if (index == pagerState.currentPage) Color(0xFF2798FF.toInt())
                else Color(0x86868633.toInt())

            Spacer(
                modifier = Modifier
                    .width(sWidth)
                    .height(2.dp)
                    .clip(RoundedCornerShape(0.5.dp))
                    .background(color)
                    .animateContentSize()
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewDetailCard() {

    Spacer(modifier = Modifier.height(4.dp))

    Card(
        modifier = Modifier
            .drawColoredShadow(
                color = Color(0xFFDAE9FF),
                borderRadius = 20.dp,
                offsetY = 1.dp,
                spread = 2.dp
            )
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp),
        border = BorderStroke(1.dp, Color(0xFFDAE9FF)),
        shape = MaterialTheme.shapes.large.copy(CornerSize(20.dp)),
        backgroundColor = Color.White,
        elevation = 0.2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        ) {

            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(0.6f),
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .height(28.dp)
                        .wrapContentWidth(Alignment.Start),
                    text = "Have a Prescription?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        //fontFamily = FontFamily(Font(R.font.plus jakarta display)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF505050),
                    )
                )
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.TopStart),
                    text = "Upload a prescription and we will deliver your medicines",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        //fontFamily = FontFamily(Font(R.font.plus jakarta display)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF6B6B6B),
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    border = BorderStroke(1.dp, Color(0xFF9BBEF3)),
                    shape = MaterialTheme.shapes.large.copy(CornerSize(12.dp)),
                    modifier = Modifier
                        /*.border(
                            width = 1.dp,
                            color = Color(0xFF9BBEF3),
                            shape = RoundedCornerShape(size = 16.dp)
                        )*/
                        .padding(end = 8.dp, start = 8.dp, bottom = 8.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFF2F8FF)),
                    contentPadding = PaddingValues(
                        start = 18.dp,
                        top = 5.dp,
                        end = 20.dp,
                        bottom = 5.dp,
                    ),
                    onClick = {

                    }
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_presc_upload),
                        modifier = Modifier.size(16.dp),
                        contentDescription = "drawable icons",
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Upload",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            //fontFamily = FontFamily(Font(R.font.plus jakarta display)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2E77E5),
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            GifImage(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .align(Alignment.Bottom),
                src = R.drawable.ic_prescription
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent() {
    ViewSearch()
    ViewPromo()
    ViewDetailCard()
}
