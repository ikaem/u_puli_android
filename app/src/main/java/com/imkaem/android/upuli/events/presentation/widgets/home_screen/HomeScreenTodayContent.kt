import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.domain.models.date
import com.imkaem.android.upuli.events.domain.models.time
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenDayState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.EventsSectionTotalPeriodCount
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContentColorVariant
import com.imkaem.android.upuli.events.presentation.widgets.TodayEventMetadataContainer
import com.imkaem.android.upuli.ui.theme.ColorBlue60
import com.imkaem.android.upuli.ui.theme.ColorWhite
import kotlin.Int
import kotlin.Unit

@Composable
fun HomeScreenTodayContent(
    todayEventsState: HomeScreenDayState?,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(all = 10.dp)
    ) {
        EventsContentTitle(
            title = "DANAS",
            markerColor = ColorBlue60,
        )
        if (todayEventsState == null) {
//            EventsScreenTodayNoEventContent()
            NoEventsContent(
                colorVariant = NoEventsContentColorVariant.LIGHT
            )
            return
        }
        HomeScreenTodayEventContent(
            featuredEvent = todayEventsState.featuredEvent,
            eventsCount = todayEventsState.dayEventsCount,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTodayEvents = onNavigateToTodayEvents,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun HomeScreenTodayEventContent(
    featuredEvent: EventModel,
    eventsCount: Int,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        featuredEvent.title,
        fontSize = 24.sp,
        color = ColorWhite,
        fontWeight = FontWeight.Bold,
        modifier = modifier.clickable {
            onNavigateToEvent(featuredEvent.id)
        }
    )
    Spacer(Modifier.height(15.dp))
    TodayEventMetadataContainer(
        text = featuredEvent.location,
        iconImageVector = Icons.Filled.LocationOn,
        iconContentDescription = "Venue location",
    )

    Spacer(Modifier.height(10.dp))
    Row {
        /* TODO this should be unified with the one above */
        TodayEventMetadataContainer(
            text = featuredEvent.date(),
            iconImageVector = Icons.Filled.CalendarMonth,
            iconContentDescription = "Event date",
        )

        Spacer(Modifier.width(10.dp))
        TodayEventMetadataContainer(
            text = featuredEvent.time(),
            iconImageVector = Icons.Filled.AccessTime,
            iconContentDescription = "Event time",
        )

    }
    Spacer(Modifier.height(15.dp))
    EventsSectionTotalPeriodCount(
        count = eventsCount,
        color = ColorBlue60,
        onNavigateToPeriodEvents = onNavigateToTodayEvents,
    )
}