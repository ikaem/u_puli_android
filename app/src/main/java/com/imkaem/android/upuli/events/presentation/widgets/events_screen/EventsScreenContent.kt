import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60

@Composable
fun EventsScreenContent(
    eventsState: EventsScreenState,
    /* TODO test only for flow */
//    flowedEvents: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val isLoading = eventsState.isLoading

    if (isLoading) {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
        return;
    }

    Column(
        modifier = modifier.padding(padding)
    ) {


        EventsScreenTodayContent(
            todayEventsState = eventsState.todayEventsState,
//            todayEventsState = EventsScreenDayState(
//                featuredEvent = EventModel(
//                    id = 0,
//                    title = "Tomorrow's Featured Event",
//                    description = "This is a placeholder for tomorrow's featured event.",
//                    date = "05.07.2025",
//                    location = "Tomorrow's Location that is very loooong",
////                    location = "Tomorrow's Location",
//                    time = "10:00 AM",
//                    url = "https://example.com/tomorrow-event",
//                    imageUrl = "https://example.com/tomorrow-event-image.jpg",
//                    isBookmarked = false,
//                ),
//                dayEventsCount = 5 // Placeholder count
//            ),
//            todayEventsState = null,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTodayEvents = onNavigateToTodayEvents,
        )
        EventsScreenTomorrowContent(
            tomorrowEventsState = eventsState.tomorrowEventsState,
//            tomorrowEventsState = EventsScreenDayState(
//                featuredEvent = EventModel(
//                    id = 0,
//                    title = "Tomorrow's Featured Event",
//                    description = "This is a placeholder for tomorrow's featured event.",
//                    date = "05.07.2025",
//                    location = "Tomorrow's Location that is very loooong",
////                    location = "Tomorrow's Location",
//                    time = "10:00 AM",
//                    url = "https://example.com/tomorrow-event",
//                    imageUrl = "https://example.com/tomorrow-event-image.jpg",
//                    isBookmarked = false,
//                ),
//                dayEventsCount = 5 // Placeholder count
//            ),
//            tomorrowEventsState = null,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
        )
        Spacer(Modifier.height(10.dp))
        EventsContent(
            events = eventsState.allUpcomingEvents,
//            events = flowedEvents,
//            events = emptyList(),
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
//            modifier = Modifier.padding(top = 10.dp)

        )
//        EventsScreenUpcomingContent(
//            eventsState.allUpcomingEvents,
//            onNavigateToEvent,
//            onToggleEventIsBookmarked,
//        )
    }

}


