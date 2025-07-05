import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenState
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale
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
        Text(
            "DANAS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(5.dp))
        if (todayEventsState == null) {
            EventsScreenTodayNoEventContent()
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
private fun EventsScreenTodayNoEventContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Nema događaja za danas",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp)
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

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Venue location",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 5.dp)
        )
        Text(
            featuredEvent.location,
            fontSize = 14.sp,
        )
    }
    Spacer(Modifier.height(10.dp))
    Text(
        featuredEvent.title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.clickable {
            onNavigateToEvent(featuredEvent.id)
        }
    )
    Spacer(Modifier.height(15.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = "Event time",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 5.dp)
        )
        Text(
            featuredEvent.date,
            fontSize = 14.sp,
        )
        Spacer(Modifier.width(30.dp))
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = "Event time",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 5.dp)
        )
        Text(
            featuredEvent.time,
            fontSize = 14.sp,
        )
    }
    Spacer(Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onNavigateToTodayEvents()
        }
    ) {
        Text(
            "Ukupno $eventsCount događaja danas",
            fontSize = 12.sp,
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            )
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
            contentDescription = "More today events",
            modifier = Modifier
                .size(20.dp)
                .padding(start = 5.dp)
        )
    }
}