package com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState

@Composable
fun BookmarkedEventsScreenContent(
    eventsState: BookmarkedEventsScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(padding)
    ) {
        Text(
            "Ovo je dio s filterima ",
            modifier = Modifier.padding(10.dp)
        )
        Spacer(Modifier.height(10.dp))
        BookmarkedEventsScreenEventsContent(
            events = eventsState.bookmarkedEvents,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        )
    }

}