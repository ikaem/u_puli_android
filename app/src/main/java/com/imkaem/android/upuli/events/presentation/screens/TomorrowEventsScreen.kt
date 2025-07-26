package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.TodayEventsScreenState
import com.imkaem.android.upuli.events.presentation.view_models.TodayEventsScreenViewModel
import com.imkaem.android.upuli.events.presentation.view_models.TomorrowEventsScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.today_events_screen.TodayEventsScreenContent
import com.imkaem.android.upuli.events.presentation.widgets.tomorrow_events_screen.TomorrowEventsScreenContent


// TODO not sure if this should be unified with tomorrowevents screen, or not - not sure...
@Composable
fun TomorrowEventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
) {

    val viewModel: TomorrowEventsScreenViewModel = viewModel()
    val eventsState = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        }
    ) { it ->

        TomorrowEventsScreenContent(
            eventsState = eventsState,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            padding = it,
        )
    }


}