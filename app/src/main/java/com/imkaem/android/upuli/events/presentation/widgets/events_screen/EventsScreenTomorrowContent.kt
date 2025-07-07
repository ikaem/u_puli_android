import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.EventsSectionTotalPeriodCount
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContentColorVariant
import com.imkaem.android.upuli.events.presentation.widgets.TomorrowEventMetadataContainer
import com.imkaem.android.upuli.ui.theme.ColorBlue60
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorGreyPink60
import com.imkaem.android.upuli.ui.theme.ColorWhite

@Composable
fun EventsScreenTomorrowContent(
    tomorrowEventsState: EventsScreenDayState?,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(ColorBlue60)
            .padding(all = 10.dp)


    ) {
//        Text(
//            "SUTRA",
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold,
//        )
        EventsContentTitle(
            title = "SUTRA",
            markerColor = ColorGreyPink100,
        )
        Spacer(Modifier.height(5.dp))
        if (tomorrowEventsState == null) {
//            EventsScreenTomorrowNoEventContent()
            NoEventsContent(
                colorVariant = NoEventsContentColorVariant.DARK,
            )
            return
        }
        EventsScreenTomorrowEventContent(
            featuredEvent = tomorrowEventsState.featuredEvent,
            eventsCount = tomorrowEventsState.dayEventsCount,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun EventsScreenTomorrowEventContent(
    featuredEvent: EventModel,
    eventsCount: Int,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
    modifier: Modifier = Modifier,
) {


    /* TODO this should be moved to view model logic */
//    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
//    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
//
//    val dateString = dateFormatter.format(featuredEvent.date)
//    val timeString = timeFormatter.format(featuredEvent.date)

    Row(
//        modifier = modifier.fillMaxWidth().clickable {
//            onNavigateToEvent(featuredEvent.id)
//        },
        verticalAlignment = Alignment.CenterVertically,
    ) {

        TomorrowEventMetadataContainer(
            text = featuredEvent.location,
            iconImageVector = Icons.Filled.LocationOn,
            iconContentDescription = "Location",
            modifier = Modifier
                .weight(1f, true),
//            textModifier = Modifier.weight(1f, true),
            textMaxLines = 1,
            textOverflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = Modifier.weight(1f, true),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            TomorrowEventMetadataContainer(
                text = featuredEvent.date,
                iconImageVector = Icons.Filled.CalendarMonth,
                iconContentDescription = "Tomorrow event date",
            )
            Spacer(Modifier.width(10.dp))
            TomorrowEventMetadataContainer(
                text = featuredEvent.time,
                iconImageVector = Icons.Filled.AccessTime,
                iconContentDescription = "Tomorrow event time",
            )
        }
    }
    Spacer(Modifier.height(10.dp))
    Text(
        featuredEvent.title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = modifier.clickable {
            onNavigateToEvent(featuredEvent.id)
        },
        color = ColorWhite,

        )
    Spacer(Modifier.height(10.dp))
    EventsSectionTotalPeriodCount(
        count = eventsCount,
        color = ColorGreyPink60,
        onNavigateToPeriodEvents = onNavigateToTomorrowEvents,
//        modifier = Modifier.fillMaxWidth()
    )

}

//@Composable
//private fun EventsScreenTomorrowNoEventContent() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Text(
//            "Nema događaja za sutra",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}