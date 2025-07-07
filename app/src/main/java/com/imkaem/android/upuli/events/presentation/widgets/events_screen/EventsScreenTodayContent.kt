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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
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
fun EventsScreenTodayContent(
    todayEventsState: EventsScreenDayState?,
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
        EventsScreenTodayEventContent(
            featuredEvent = todayEventsState.featuredEvent,
            eventsCount = todayEventsState.dayEventsCount,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTodayEvents = onNavigateToTodayEvents,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun EventsScreenTodayEventContent(
    featuredEvent: EventModel,
    eventsCount: Int,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    modifier: Modifier = Modifier,
) {

    /* TODO TODAY date and time formatting should be done in view model later */
//    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
//    val timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
//
//    val dateString = dateFormat.format(featuredEvent.date)
//    val timeString = timeFormat.format(featuredEvent.date)

//    Spacer(Modifier.height(5.dp))
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

//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            //            .border(
//            //                BorderStroke(2.dp, SolidColor(Color.Red)),
//            //                shape = RoundedCornerShape(10.dp)
//            //            )
//
//            .clip(shape = RoundedCornerShape(5.dp))
//            .background(
////                    Color.Gray
//                ColorGreyGreen60,
//            )
//            .padding(horizontal = 5.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.LocationOn,
//            contentDescription = "Venue location",
//            tint = ColorGreyBlue60,
//            modifier = Modifier
//                .size(18.dp)
//                .padding(end = 5.dp)
//        )
//        Text(
//            featuredEvent.location,
//            fontSize = 12.sp,
////            color = ColorGrey10,
//            color = ColorGreyGreen10
//
//        )
//    }

    Spacer(Modifier.height(10.dp))
    Row {
        /* TODO this should be unified with the one above */
        TodayEventMetadataContainer(
            text = featuredEvent.date,
            iconImageVector = Icons.Filled.CalendarMonth,
            iconContentDescription = "Event date",
        )
//        Row(
//            /* TODO borders here */
//            verticalAlignment = Alignment.CenterVertically,
//            /* TODO make a widget out of this, so we can always have the same button in simple way */
//            modifier = Modifier
//                //            .border(
//                //                BorderStroke(2.dp, SolidColor(Color.Red)),
//                //                shape = RoundedCornerShape(10.dp)
//                //            )
//
//                .clip(shape = RoundedCornerShape(5.dp))
//                .background(
////                    Color.Gray
//                    ColorGreyGreen60,
//                )
//                .padding(horizontal = 5.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Filled.CalendarMonth,
//                contentDescription = "Event time",
////                tint = ColorGreyGreen10,
//                tint = ColorGreyBlue60,
//                modifier = Modifier
//                    .size(20.dp)
//                    .padding(end = 5.dp)
//            )
//            Text(
//                featuredEvent.date,
//                fontSize = 12.sp,
//                color = ColorGreyGreen10
//                //            modifier = Modifier.padding(5.dp)
//            )
//        }

        Spacer(Modifier.width(10.dp))
        TodayEventMetadataContainer(
            text = featuredEvent.time,
            iconImageVector = Icons.Filled.AccessTime,
            iconContentDescription = "Event time",
//            modifier = Modifier.weight(1f, true),
//            textMaxLines = 1,
//            textOverflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            //        modifier = Modifier.fillMaxWidth()
//            modifier = Modifier
//                //            .border(
//                //                BorderStroke(2.dp, SolidColor(Color.Red)),
//                //                shape = RoundedCornerShape(10.dp)
//                //            )
//                /* TODO unify this so it can be reused */
//                .clip(shape = RoundedCornerShape(5.dp))
//                .background(
////                    Color.Blue,
//                    ColorGreyGreen60
//                )
//                .padding(horizontal = 5.dp)
//
//        ) {
//            Icon(
//                imageVector = Icons.Filled.AccessTime,
//                contentDescription = "Event time",
////                tint = ColorGreyGreen10,
//                tint = ColorGreyBlue60,
//                modifier = Modifier
//                    .size(20.dp)
//                    .padding(end = 5.dp)
//            )
//            Text(
//                featuredEvent.time,
//                fontSize = 12.sp,
//                color = ColorGreyGreen10
//            )
//        }
    }
    Spacer(Modifier.height(15.dp))
    EventsSectionTotalPeriodCount(
        count = eventsCount,
        color = ColorBlue60,
        onNavigateToPeriodEvents = onNavigateToTodayEvents,
    )
}