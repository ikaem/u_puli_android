import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenState

@Composable
fun EventsScreenContent(
    eventsState: EventsScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(padding)
    ) {
        EventsScreenTodayContent(
            todayEventsState = eventsState.todayEventsState,
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
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
        )
        Spacer(Modifier.height(10.dp))
        EventsScreenUpcomingContent(
            eventsState.allUpcomingEvents,
            onNavigateToEvent,
            onToggleEventIsBookmarked,
        )
    }

}


