package com.imkaem.android.upuli.events.presentation.widgets

import EventBriefItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.domain.models.EventModel

/* TODO deprecated */
@Composable
fun EventBriefItems(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier = Modifier,
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
                onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
            )

            val isLast = index == events.size - 1
            if (!isLast) {
                HorizontalDivider()
            }
        }
    }

}