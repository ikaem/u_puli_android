package com.imkaem.android.upuli.events.presentation.screens

import EventsScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsScreenViewModel
import com.imkaem.android.upuli.ui.theme.UPuliTheme

/* TODO this screen, all its widgets, view model, state, and so on, should be renamed to HomeScreen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
) {

    val viewModel: EventsScreenViewModel = viewModel()
    val eventsState = viewModel.state.value

    Scaffold(
        /* TODO not sure if this contributes to anything in this particular case */
//        containerColor = Color.Yellow,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },
        content = { padding ->
            EventsScreenContent(
                eventsState = eventsState,
                onNavigateToEvent = onNavigateToEvent,
                onNavigateToTodayEvents = onNavigateToTodayEvents,
                onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
                onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
                padding = padding,
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun EventsScreenPreview() {
    UPuliTheme {
        EventsScreen(
            onNavigateToEvent = {  /* no-op */ },
            onNavigateToTodayEvents = { /* no-op */ },
            onNavigateToBookmarks = { /* no-op */ },
            onNavigateToTomorrowEvents = { /* no-op */ }
        )
    }
}