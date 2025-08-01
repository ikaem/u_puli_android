package com.imkaem.android.upuli.events.presentation.screens

import HomeScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenViewModel
import com.imkaem.android.upuli.ui.theme.UPuliTheme

/* TODO this screen, all its widgets, view model, state, and so on, should be renamed to HomeScreen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
) {

    val viewModel: HomeScreenViewModel = viewModel()
    val screenState = viewModel.state.collectAsStateWithLifecycle().value


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        }
    ) { padding ->

        HomeScreenContent(
            screenState = screenState,
            todayDateString = viewModel.todayString,
            tomorrowDateString = viewModel.tomorrowString,
            onNavigateToEvent = onNavigateToEvent,
            onNavigateToTodayEvents = onNavigateToTodayEvents,
            onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            onSelectTab = viewModel::onSelectTab,
            padding = padding,
        )
    }


}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    UPuliTheme {
        HomeScreen(
            onNavigateToEvent = {  /* no-op */ },
            onNavigateToTodayEvents = { /* no-op */ },
            onNavigateToBookmarks = { /* no-op */ },
            onNavigateToTomorrowEvents = { /* no-op */ }
        )
    }
}