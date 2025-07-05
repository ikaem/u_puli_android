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
    /* TODO: not sure if this shoule maybe be part */
    val todayDateString = viewModel.fromDateString

    Scaffold(
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
            )
        }
    ) { it ->

        /* TODO all these things kida repeat - in bunch of screens - all this can be unified  */
        BookmarkedEventsScreenContent(
            eventsState = eventsState,
            fromDateString = todayDateString,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            padding = it,
        )
    }
}