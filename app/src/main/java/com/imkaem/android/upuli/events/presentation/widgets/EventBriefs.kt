package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun EventBriefs(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    if (events.isEmpty()) {
        NoEventsContent(
            colorVariant = UIElementColorVariant.GREEN,
        )
        return
    }

    // TODO this should be replaced with a list of events
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            count = events.size,
            key = { index -> events[index].id }
        ) { index ->
            val event = events[index]

            EventBrief(
                event = event,
                onNavigateToEvent = onNavigateToEvent,
                onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            )

            val isLastItem = index == events.lastIndex
            if (isLastItem) return@items

            Spacer(Modifier.height(5.dp))
            HorizontalDivider(
                color = ColorYellowLight
            )
            Spacer(Modifier.height(5.dp))

        }
    }

}