import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenState

@Composable
fun EventsScreenContent(
    eventsState: EventsScreenState,
    onNavigateToEvent: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(padding)
    ) {
        EventsScreenTodayContent(
            todayEventsState = eventsState.todayEventsState,
            onNavigateToEvent = onNavigateToEvent,
        )
        EventsScreenTomorrowContent(
            tomorrowEventsState = eventsState.tomorrowEventsState,
            onNavigateToEvent = onNavigateToEvent,
        )
        Spacer(Modifier.height(10.dp))
        EventsScreenUpcomingContent(
            eventsState.allUpcomingEvents,
            onNavigateToEvent,
        )
    }

}


