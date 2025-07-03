package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen.BookmarkedEventsScreenContent

@Composable
fun BookmarkedEventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
) {

    val viewModel: BookmarkedEventsScreenViewModel = viewModel()
    val eventsState = viewModel.state.value

    Scaffold(
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
            )
        }
    ) { it ->

        BookmarkedEventsScreenContent(
            eventsState = eventsState,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = { /* no-op */ },
            padding = it,
        )
    }
}