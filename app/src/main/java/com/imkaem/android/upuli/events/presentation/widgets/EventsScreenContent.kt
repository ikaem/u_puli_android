import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun EventsScreenContent(
    padding: PaddingValues,
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier.padding(padding)
    ) {
        TodayEventContent()
        TomorrowEventContent()
        Spacer(Modifier.height(10.dp))
        UpcomingEventsContent(
            events,
            onNavigateToEvent,
        )
    }

}

@Composable
private fun TodayEventContent() {
    Column(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Text(
            "DANAS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Istarsko narodno kazalište",
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Venue location",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp)
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(
            "Tko pod drugim jamu kopa, sam u nju pada uvijek jest, da",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "21.10.2025.",
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Event time",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp)
            )
            Spacer(Modifier.width(30.dp))
            Text(
                "20:00",
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Event time",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp)
            )
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "I JOŠ 2 DOGAĐAJA",
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
}

@Composable
private fun TomorrowEventContent() {
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
                    "Istarsko narodno kazalište u Puli ",
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                )
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Tomorrow venue location",
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
                        "22.10.2025.",
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
                        "20:00",
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
            "Subotom u knjižnici: Kako je Imra pomirila instrumente",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Spacer(Modifier.height(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "I JOŠ 2 DOGAĐAJA",
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
}

@Composable
private fun UpcomingEventsContent(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(all = 10.dp)
    ) {
        Text(
            "SVI NADOLAZEĆI DOGAĐAJI",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(5.dp))
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {

            items(
                count = events.size,
                key = { index -> events[index].id }
            ) { index ->

                val event = events[index]

                EventBriefItem(
                    event = event,
                    onNavigateToEvent = onNavigateToEvent,
                    modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)
                )

                val isLast = index == events.size - 1
                if (!isLast) {
                    HorizontalDivider()
                }
            }

        }
    }
}