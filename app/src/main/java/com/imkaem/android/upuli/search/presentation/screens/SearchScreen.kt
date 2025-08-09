package com.imkaem.android.upuli.search.presentation.screens

/* NOTE: Idea here is that in future, we can search many things */


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
import com.imkaem.android.upuli.search.presentation.view_models.SearchScreenViewModel
import com.imkaem.android.upuli.search.presentation.widgets.search_screen.SearchScreenContent


// TODO not sure if this should be unified with tomorrowevents screen, or not - not sure...
@Composable
fun SearchScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
) {

    val viewModel: SearchScreenViewModel = viewModel()
    val eventsState = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        }
    ) { it ->

        SearchScreenContent(
            eventsState = eventsState,
            onNavigateToEvent = onNavigateToEvent,
            onChangeSearchQuery = viewModel::onChangeSearchQuery,
            onSubmitSearchQuery = viewModel::onSubmitSearchQuery,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            padding = it,
        )
    }
}