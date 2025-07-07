package com.imkaem.android.upuli.events.presentation.widgets.tomorrow_events_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.view_models.TomorrowEventsScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60

@Composable
fun TomorrowEventsScreenContent(
    eventsState: TomorrowEventsScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val isLoading = eventsState.isLoading;
    if (isLoading) {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
        return;
    }

    Column(
        modifier = modifier.padding(padding)
    ) {


        EventsContent(
            events = eventsState.events,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            title = {
                EventsContentTitle(
                    title = "DOGAĐAJI SUTRA",
                    markerColor = ColorGreyGreen60,
                    textColor = ColorGrey60,
                    bottomSpacing = 5.dp,
                )
            },
        )
    }

}