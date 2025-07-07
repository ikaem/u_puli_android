package com.imkaem.android.upuli.events.presentation.screens

import EventScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.EventScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToEventWebView: (id: Int) -> Unit,

    ) {
    val viewModel: EventScreenViewModel = viewModel()
    val eventState = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },
        content = { padding ->
            EventScreenContent(
                padding = padding,
                eventState = eventState,
                onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
                onNavigateToEventWebView = onNavigateToEventWebView,
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun EventScreenPreview() {
    EventScreen(
        onNavigateBack = { /* no-op */ },
        onNavigateToBookmarks = { /* no-op */ },
        onNavigateToEventWebView = { /* no-op */ }
    )
}