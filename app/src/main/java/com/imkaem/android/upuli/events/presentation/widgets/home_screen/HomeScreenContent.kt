import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60

@Composable
fun HomeScreenContent(
    eventsState: HomeScreenState,
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


        HomeScreenTodayContent(
            todayEventsState = eventsState.todayEventsState,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTodayEvents = onNavigateToTodayEvents,
        )
        HomeScreenTomorrowContent(
            tomorrowEventsState = eventsState.tomorrowEventsState,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
        )
        Spacer(Modifier.height(10.dp))
        EventsContent(
            events = eventsState.allUpcomingEvents,
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


