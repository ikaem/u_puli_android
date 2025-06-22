import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenDayState
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EventsScreenTomorrowContent(
    tomorrowEventsState: EventsScreenDayState?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(all = 10.dp)


    ) {
        Text(
            "SUTRA",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(5.dp))
        if (tomorrowEventsState == null) {
            EventsScreenTomorrowNoEventContent()
            return
        }
        EventsScreenTomorrowEventContent(
            featuredEvent = tomorrowEventsState.featuredEvent,
            eventsCount = tomorrowEventsState.dayEventsCount,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun EventsScreenTomorrowEventContent(
    featuredEvent: EventModel,
    eventsCount: Int,
    modifier: Modifier = Modifier,
) {


    /* TODO this should be moved to view model logic */
//    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
//    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
//
//    val dateString = dateFormatter.format(featuredEvent.date)
//    val timeString = timeFormatter.format(featuredEvent.date)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f, true),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {

            Text(
                text = featuredEvent.location,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f),
                maxLines = 1,
            )
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
                modifier = Modifier
                    .size(16.dp)
                    .padding(start = 5.dp)
            )
        }

        Row(
            modifier = Modifier.weight(1f, true),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {

                Text(
                    featuredEvent.date,
                    fontSize = 12.sp,
                )
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Tomorrow event date",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(start = 5.dp)
                )
            }
            Spacer(Modifier.width(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {

                Text(
                    featuredEvent.time,
                    fontSize = 12.sp,
                )
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Tomorrow event time",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(start = 5.dp)
                )
            }
        }
    }
    Spacer(Modifier.height(5.dp))
    Text(
        featuredEvent.title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    )
    Spacer(Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "Ukupno $eventsCount događaja sutra",
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

@Composable
private fun EventsScreenTomorrowNoEventContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Nema događaja za sutra",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}