package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState
import com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen.BookmarkedEventsScreenContent

@Composable
fun BookmarkedEventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            UPuliTopAppBar(
                title = "Spremljeni događaji",
                onNavigateBack = onNavigateBack,
            )
        }
    ) { it ->

        BookmarkedEventsScreenContent(
            eventsState = BookmarkedEventsScreenState(
                bookmarkedEvents = emptyList(), // Replace with actual data
                isLoading = false,
                error = null
            ),
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = { /* no-op */ },
            padding = it,
        )
    }
}