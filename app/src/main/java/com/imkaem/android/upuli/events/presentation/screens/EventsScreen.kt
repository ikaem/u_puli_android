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

//import java.util.Date

/* TODO: this is probably best to be called HomeScreen */
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
//                onToggleEventIsBookmarked = { it ->
//                    viewModel.onToggleEventIsBookmarked(it)
//                },
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