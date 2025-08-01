package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.ui.theme.ColorGrey5

/* TODO check to reuse this in today events screen, tomorrow events screen, and bookmarked events screen */

@Composable
fun EventsContent(
    events: List<EventModel>,
//    contentTitle: String,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    title : @Composable (() -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ColorGrey5)
            .padding(all = 10.dp)
    ) {
        title()
        if (events.isEmpty()) {
            NoEventsContentDeprecated(
                modifier = Modifier.fillMaxSize(),
                colorVariant = NoEventsContentColorVariant.DARK,
            )
            return
        }
        EventsContentPopulated(
            events = events,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/* TODO this could be reused probably */
//@Composable
//private fun EventsContentEmpty(
//    modifier: Modifier = Modifier,
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier.fillMaxWidth()
//    ) {
//        Text(
//            "Nema nadolazećih događaja",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}

@Composable
private fun EventsContentPopulated(
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

