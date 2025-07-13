package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.TodayEventsScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.today_events_screen.TodayEventsScreenContent


// TODO not sure if this should be unified with tomorrowevents screen, or not - not sure...
@Composable
fun TodayEventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
) {

    val viewModel: TodayEventsScreenViewModel = viewModel()
    val eventsState = viewModel.state.collectAsStateWithLifecycle().value
    /* TODO no-flow implementation - keep for now */
//    val eventsState = viewModel.state.value

    Scaffold(
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        }
    ) { it ->

        TodayEventsScreenContent(
            eventsState = eventsState,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            padding = it,
        )
    }


}