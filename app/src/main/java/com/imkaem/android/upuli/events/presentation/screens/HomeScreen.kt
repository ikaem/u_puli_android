package com.imkaem.android.upuli.events.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.R
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenViewModel
import com.imkaem.android.upuli.ui.theme.ColorDarkText
import com.imkaem.android.upuli.ui.theme.ColorGreenDark
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.UPuliTheme
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorLightText
import com.imkaem.android.upuli.ui.theme.ColorWhite

/* TODO this screen, all its widgets, view model, state, and so on, should be renamed to HomeScreen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
) {

    val viewModel: HomeScreenViewModel = viewModel()
    val screenState = viewModel.state.collectAsStateWithLifecycle().value


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->

        /* the card starts here */
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.drawWithCache {
                        onDrawWithContent {
                            drawLine(
                                color = ColorGreenDark,
//                                start = Offset.Zero,
                                start = Offset(5f, 0f),
                                end = Offset(5f, this.size.height),
                                strokeWidth = 8f
                            )
                            drawContent()
                        }
                    }
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "DANAS",
                        color = ColorDarkText,
                        fontWeight = FontWeight.Bold,


                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        "31.07.2025.",
                        color = ColorLightText,
                        fontWeight = FontWeight.Bold,

                    )
                }
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
//                        .padding(
//                            10.dp
//                            //                        top = padding.calculateTopPadding(),
//                            //                        bottom = padding.calculateBottomPadding(),
//                            //                        start = 10.dp,
//                            //                        end = 10.dp,
//                        )
                        .background(ColorGreenLight)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(0.35f).heightIn(0.dp, 200.dp, )
                        ) {
                            AsyncImage(
                                model = "https://images.unsplash.com/photo-1598517101691-375e380bca26?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                                contentDescription = "Event image",
//                                contentScale = ContentScale.FillWidth,
                                contentScale = ContentScale.FillHeight,
                                //                        modifier = Modifier.fillMaxWidth(),
                                error = painterResource(R.drawable.event)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier.weight(0.65f)

                        ) {
                            Text(
                                "Ljeto u knjižnici: Bogatstvo mora asd sad sad ",
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                )
                            Spacer(Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .background(ColorGreenDark)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp,
                                    ),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = "Venue location",
                                    tint = ColorWhite,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .padding(end = 5.dp, top = 4.dp)
                                )
                                Text(
                                    "Gradska knjižnica i čitaonica Pula",
                                    color = ColorWhite,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .background(ColorGreenDark)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp,
                                    ),

                                ) {
                                Icon(
                                    imageVector = Icons.Filled.CalendarMonth,
                                    contentDescription = "Event date",
                                    tint = ColorWhite,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .padding(end = 5.dp, top = 4.dp)
                                )
                                Text(
                                    "31.07.2025.",
                                    color = ColorWhite,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .background(ColorGreenDark)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp,
                                    ),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AccessTime,
                                    contentDescription = "Event time",
                                    tint = ColorWhite,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .padding(
                                            end = 5.dp, top = 4.dp
                                        )
                                )
                                Text(
                                    "18:00",
                                    color = ColorWhite,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Icon(
                                modifier = Modifier.clickable {},
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark event",
                                tint = ColorGreyPink100,

                            )

                        }

                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        thickness = 2.dp,
                        color = ColorWhite,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                "I još 3 događaja",
                                fontSize = 14.sp,
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline
                                )

                                )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                                contentDescription = "More events",
                                modifier = Modifier.width(22.dp).padding(start = 5.dp)
                                )
                        }
                        Spacer(Modifier.height(5.dp))
                        Text(
                            "Ljeto u knjižnici: Bogatstvo mora",
                            fontSize = 12.sp,
                            )
                        Text(
                            "Ljeto u knjižnici: Još nešto",
                            fontSize = 12.sp,
                            )
                        Text(
                            "...",
                            fontSize = 12.sp,
                            )
                    }

                }
            }
        }
    }


//    Scaffold(
//        /* TODO not sure if this contributes to anything in this particular case */
////        containerColor = Color.Yellow,
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            UPuliTopAppBar(
//                onNavigateToBookmarks = onNavigateToBookmarks,
//            )
//        },
//        content = { padding ->
//            HomeScreenContent(
//                screenState = screenState,
//                onNavigateToEvent = onNavigateToEvent,
//                onNavigateToTodayEvents = onNavigateToTodayEvents,
//                onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
//                onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
//                onSelectTab = viewModel::onSelectTab,
//                padding = padding,
//            )
//        }
//    )
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    UPuliTheme {
        HomeScreen(
            onNavigateToEvent = {  /* no-op */ },
            onNavigateToTodayEvents = { /* no-op */ },
            onNavigateToBookmarks = { /* no-op */ },
            onNavigateToTomorrowEvents = { /* no-op */ }
        )
    }
}