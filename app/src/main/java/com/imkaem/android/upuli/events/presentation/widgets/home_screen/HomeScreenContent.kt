import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen100
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorWhite

@Composable
fun HomeScreenContent(
    screenState: HomeScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    onSelectTab: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val isLoading = screenState.isLoading

    if (isLoading) {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
        return;
    }


//    val selectedTabIndex = remember {
//        mutableIntStateOf(0)
//    }


    Column(
        /* TODO padding could have been applied to modifier prior to passing both as props */
        modifier = modifier.padding(padding)
    ) {

        TabRow(
//            selectedTabIndex = selectedTabIndex.intValue,
            selectedTabIndex = screenState.selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = ColorGreyGreen100,
            contentColor = ColorWhite,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(
                        tabPositions[screenState.selectedTabIndex]
//                        tabPositions[selectedTabIndex.intValue]
                    ),
                    color = ColorGreyPink100,
                    height = 1.dp,
                )
            }
        ) {
            listOf<String>("AKTUALNO", "SVI DOGAĐAJI").forEachIndexed { index, value ->

                Tab(
//                    selected = selectedTabIndex.intValue == index,
                    selected = screenState.selectedTabIndex == index,
                    onClick = {
//                        selectedTabIndex.intValue = index
                        onSelectTab(index)
                    }
                ) {
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }


            }
        }

        Spacer(Modifier.height(10.dp))

        when (screenState.selectedTabIndex) {
            0 -> {

                Column(
                    modifier.verticalScroll(
                        rememberScrollState()
                    )
                ) {


                    HomeScreenTodayContent(
                        todayEventsState = screenState.todayEventsState,
                        onNavigateToEvent = onNavigateToEvent,
                        onNavigateToTodayEvents = onNavigateToTodayEvents,
                    )
                    HomeScreenTomorrowContent(
                        tomorrowEventsState = screenState.tomorrowEventsState,
                        onNavigateToEvent = onNavigateToEvent,
                        onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
                    )
                }
            }

            else -> {
                EventsContent(
                    events = screenState.allUpcomingEvents,
                    onNavigateToEvent = onNavigateToEvent,
                    onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                    title = {
                        EventsContentTitle(
                            title = "SVI NADOLAZEĆI DOGAĐAJI",
                            markerColor = ColorGreyGreen60,
                            textColor = ColorGrey60,
                            bottomSpacing = 5.dp,
                        )
                    },
                )
            }
        }

    }

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


