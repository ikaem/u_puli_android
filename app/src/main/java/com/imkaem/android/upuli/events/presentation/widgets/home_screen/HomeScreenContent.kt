import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsPaused
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.events.presentation.widgets.home_screen.EventBriefs
import com.imkaem.android.upuli.events.presentation.widgets.home_screen.SoonEventsCard
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorBrownDark
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen100
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorTextWhite
import com.imkaem.android.upuli.ui.theme.ColorWhite

@Composable
fun HomeScreenContent(
    screenState: HomeScreenState,
    todayDateString: String,
    tomorrowDateString: String,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    onSelectTab: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .padding(
                top = padding.calculateTopPadding(),
                start = 10.dp,
                end = 10.dp,
                bottom = padding.calculateBottomPadding(),
            )
            .fillMaxWidth()
    ) {


//            EventsSearch()
        Spacer(Modifier.height(10.dp))

        TabRow(
            selectedTabIndex = screenState.selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            divider = {},
            indicator = {}
        ) {
            /* TODO this should probably be some kind of wrapper, to hold icon as well? */
            listOf<String>("USKORO", "SVI DOGAĐAJI").forEachIndexed { index, value ->
                val isTextSelected = screenState.selectedTabIndex == index
                Tab(
                    selected = isTextSelected,
                    onClick = {
                        onSelectTab(index)
                    },
                    selectedContentColor = ColorTextWhite,
                    unselectedContentColor = ColorTextDark,
                    modifier = Modifier
                        .let {
                            if (isTextSelected) {
                                it.background(ColorBrownDark)
                            } else {
                                it
                            }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = if (index == 0) Icons.Filled.NotificationsActive else Icons.Default.NotificationsPaused,
                            contentDescription = "Soon events icon",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(16.dp)
                        )
                        Text(
                            text = value,
                            fontSize = 14.sp,
                        )
                    }
                }

            }
        }
        Spacer(Modifier.height(20.dp))

        if (screenState.error != null) {
            Text("Greška!! :(")
            return@Column
        }

        if (screenState.isLoading) {
            LoadingIndicator(
                modifier = Modifier.fillMaxSize()
            )
            return@Column
        }

        when (screenState.selectedTabIndex) {
            0 -> {

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {

                    val todayFeaturedEvent = screenState.todayEventsState?.featuredEvent
                    val todayEventsCount =
                        screenState.todayEventsState?.dayEventsCount ?: 0
                    val todayEventsString = screenState.todayEventsState?.allEventsString ?: ""


                    SoonEventsCard(
                        colorVariant = UIElementColorVariant.GREEN,
                        cardTitle = "DANAS",
                        featuredEvent = todayFeaturedEvent,
                        totalSoonEvents = todayEventsCount,
                        allSoonEventsString = todayEventsString,
                        cardDate = todayDateString,
                        onNavigateToPeriodEvents = onNavigateToTodayEvents,
                        onNavigateToEvent = onNavigateToEvent,
                        onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                    )

                    Spacer(Modifier.height(20.dp))

                    val tomorrowFeaturedEvent =
                        screenState.tomorrowEventsState?.featuredEvent
                    val tomorrowEventsCount =
                        screenState.tomorrowEventsState?.dayEventsCount ?: 0
                    val tomorrowEventsString =
                        screenState.tomorrowEventsState?.allEventsString ?: ""

                    SoonEventsCard(
                        colorVariant = UIElementColorVariant.YELLOW,
                        cardTitle = "SUTRA",
                        featuredEvent = tomorrowFeaturedEvent,
                        totalSoonEvents = tomorrowEventsCount,
                        allSoonEventsString = tomorrowEventsString,
                        cardDate = tomorrowDateString,
                        onNavigateToPeriodEvents = onNavigateToTomorrowEvents,
                        onNavigateToEvent = onNavigateToEvent,
                        onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                    )
                }
            }

            1 -> {

                EventBriefs(
                    events = screenState.allUpcomingEvents,
                    onNavigateToEvent = onNavigateToEvent,
                    onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                )
            }
        }

//            SoonEventsCard(
//                colorVariant = UIElementColorVariant.GREEN,
//                cardTitle = "DANAS",
//                cardDate = "1.8.2025.",
//            )
//            SoonEventsCard(
//                colorVariant = UIElementColorVariant.YELLOW,
//                cardTitle = "SUTRA",
//                cardDate = "2.8.2025.",
//            )

    }

//    val isLoading = screenState.isLoading
//
//    if (isLoading) {
//        LoadingIndicator(
//            modifier = Modifier.fillMaxSize()
//        )
//        return;
//    }


//    val selectedTabIndex = remember {
//        mutableIntStateOf(0)
//    }


//    Column(
//        /* TODO padding could have been applied to modifier prior to passing both as props */
//        modifier = modifier.padding(padding)
//    ) {
//
//        TabRow(
////            selectedTabIndex = selectedTabIndex.intValue,
//            selectedTabIndex = screenState.selectedTabIndex,
//            modifier = Modifier.fillMaxWidth(),
//            containerColor = ColorGreyGreen100,
//            contentColor = ColorWhite,
//            indicator = { tabPositions ->
//                TabRowDefaults.SecondaryIndicator(
//                    Modifier.tabIndicatorOffset(
//                        tabPositions[screenState.selectedTabIndex]
////                        tabPositions[selectedTabIndex.intValue]
//                    ),
//                    color = ColorGreyPink100,
//                    height = 1.dp,
//                )
//            }
//        ) {
//            listOf<String>("AKTUALNO", "SVI DOGAĐAJI").forEachIndexed { index, value ->
//
//                Tab(
////                    selected = selectedTabIndex.intValue == index,
//                    selected = screenState.selectedTabIndex == index,
//                    onClick = {
////                        selectedTabIndex.intValue = index
//                        onSelectTab(index)
//                    }
//                ) {
//                    Text(
//                        text = value,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(bottom = 5.dp)
//                    )
//                }
//
//
//            }
//        }
//
//        Spacer(Modifier.height(10.dp))
//
//        when (screenState.selectedTabIndex) {
//            0 -> {
//
//                Column(
//                    modifier.verticalScroll(
//                        rememberScrollState()
//                    )
//                ) {
//
//
//                    HomeScreenTodayContent(
//                        todayEventsState = screenState.todayEventsState,
//                        onNavigateToEvent = onNavigateToEvent,
//                        onNavigateToTodayEvents = onNavigateToTodayEvents,
//                    )
//                    HomeScreenTomorrowContent(
//                        tomorrowEventsState = screenState.tomorrowEventsState,
//                        onNavigateToEvent = onNavigateToEvent,
//                        onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
//                    )
//                }
//            }
//
//            else -> {
//                EventsContent(
//                    events = screenState.allUpcomingEvents,
//                    onNavigateToEvent = onNavigateToEvent,
//                    onToggleEventIsBookmarked = onToggleEventIsBookmarked,
//                    title = {
//                        EventsContentTitle(
//                            title = "SVI NADOLAZEĆI DOGAĐAJI",
//                            markerColor = ColorGreyGreen60,
//                            textColor = ColorGrey60,
//                            bottomSpacing = 5.dp,
//                        )
//                    },
//                )
//            }
//        }
//
//    }

//    Column(
//        modifier = modifier.padding(padding)
//    ) {
//
//
//        HomeScreenTodayContent(
//            todayEventsState = eventsState.todayEventsState,
//            onNavigateToEvent = onNavigateToEvent,
//            onNavigateToTodayEvents = onNavigateToTodayEvents,
//        )
//        HomeScreenTomorrowContent(
//            tomorrowEventsState = eventsState.tomorrowEventsState,
//            onNavigateToEvent = onNavigateToEvent,
//            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
//        )
//        Spacer(Modifier.height(10.dp))
//        EventsContent(
//            events = eventsState.allUpcomingEvents,
//            onNavigateToEvent = onNavigateToEvent,
//            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
//            title = {
//                EventsContentTitle(
//                    title = "SVI NADOLAZEĆI DOGAĐAJI",
//                    markerColor = ColorGreyGreen60,
//                    textColor = ColorGrey60,
//                    bottomSpacing = 5.dp,
//                )
//            },
//        )
//    }

}


