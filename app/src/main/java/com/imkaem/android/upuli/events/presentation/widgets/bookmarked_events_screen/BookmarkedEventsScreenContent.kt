package com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.events.presentation.widgets.ListScreenTitle
import com.imkaem.android.upuli.events.presentation.widgets.EventBriefs

@Composable
fun BookmarkedEventsScreenContent(
    eventsState: BookmarkedEventsScreenState,
    /* TODO not used at this point */
    fromDateString: String,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

//    val isLoading = eventsState.isLoading;
//    if (isLoading) {
//        LoadingIndicator(
//            modifier = Modifier.fillMaxSize()
//        )
//        return;
//    }

    Column(
        modifier = modifier
            .padding(
                top = padding.calculateTopPadding(),
                start = 10.dp,
                end = 10.dp,
                bottom = padding.calculateBottomPadding(),
            ),
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(Modifier.height(10.dp))
        ListScreenTitle(
            icon = Icons.Filled.Bookmarks,
            title = "SPREMLJENI DOGAĐAJI",
        )
        Spacer(Modifier.height(20.dp))

        EventBriefs(
            events = eventsState.bookmarkedEvents,
            isLoading = eventsState.isLoading,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        )
    }

}