package com.imkaem.android.upuli.events.presentation.widgets.tomorrow_events_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.widgets.EventBriefItems

@Composable
fun TomorrowEventsScreenEventsContent(
    events: List<EventModel>,
    onNavigateToEvent: (id: Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(all = 10.dp)
    ) {
        Text(
            "SUTRAŠNJI DOGAĐAJI",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(5.dp))
        if (events.isEmpty()) {
            TomorrowEventsScreenEventsEmptyContent()
            return
        }
        TomorrowEventsScreenEventsPopulatedContent(
            events = events,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            modifier = Modifier.fillMaxSize()
        )
    }

}

/* TODO should be moved to same file as the above one */
@Composable
private fun TomorrowEventsScreenEventsEmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            "Ne postoji niti jedan spremljeni događaj." +
                    "\n" +
                    "Spremite jedan događaj da bi ga vidjeli ovdje",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TomorrowEventsScreenEventsPopulatedContent(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier,
) {
    EventBriefItems(
        events = events,
        onNavigateToEvent = onNavigateToEvent,
        onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        modifier = modifier,
    )
}