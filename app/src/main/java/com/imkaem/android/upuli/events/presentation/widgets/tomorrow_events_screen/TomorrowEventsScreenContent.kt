package com.imkaem.android.upuli.events.presentation.widgets.tomorrow_events_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imkaem.android.upuli.events.presentation.view_models.TodayEventsScreenState
import com.imkaem.android.upuli.events.presentation.view_models.TomorrowEventsScreenState

@Composable
fun TomorrowEventsScreenContent(
    eventsState: TomorrowEventsScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(padding)
    ) {
        TomorrowEventsScreenEventsContent(
            events = eventsState.events,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        )
    }

}