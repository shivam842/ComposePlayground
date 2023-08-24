package com.gldev.composeplayground.ui.homepage

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gldev.composeplayground.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Response

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent() {
    ViewSearch()
    ViewPromo()
}

@Composable
fun ViewSearch() {
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
    Row(
        modifier = Modifier
            .shadow(
                elevation = 17.dp, spotColor = Color(0x33307EF3), ambientColor = Color(0x26296FD8)
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
            pageSpacing = 8.dp,
            pageCount = pageCount,
            contentPadding = PaddingValues(horizontal = 22.dp)
        ) { page ->
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_banner),
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
                if (index == pagerState.currentPage) Color(0xFF2798FF.toInt()) else Color(0x86868633.toInt())
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
