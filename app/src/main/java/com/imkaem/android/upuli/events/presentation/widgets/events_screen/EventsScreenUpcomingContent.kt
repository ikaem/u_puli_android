import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel

@Composable
fun EventsScreenUpcomingContent(
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
        if (events.isEmpty()) {
            EventsScreenUpcomingNoEventsContent()
            return
        }
        EventsScreenUpcomingEventsContent(
            events = events,
            onNavigateToEvent = onNavigateToEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun EventsScreenUpcomingEventsContent(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    modifier: Modifier,
) {
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

@Composable
private fun EventsScreenUpcomingNoEventsContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Nema nadolazećih događaja",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp)
        )
    }

}