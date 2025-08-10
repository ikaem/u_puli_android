package com.imkaem.android.upuli.search.presentation.widgets.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.widgets.EventBriefs
import com.imkaem.android.upuli.search.presentation.widgets.SearchField
import com.imkaem.android.upuli.events.presentation.widgets.ListScreenTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.search.presentation.view_models.SearchScreenState

@Composable
fun SearchScreenContent(
    eventsState: SearchScreenState,
    onNavigateToEvent: (Int) -> Unit,
    onChangeSearchQuery: (String) -> Unit,
    onSubmitSearchQuery: () -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

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
            icon = Icons.Filled.Search,
            title = "PRETRAŽIVANJE",
        )
        Spacer(Modifier.height(20.dp))
        SearchField(
            searchQuery = eventsState.searchQuery,
            onChangeSearchQuery = onChangeSearchQuery,
            isLoading = eventsState.isLoading,
            onSubmitSearchQuery = onSubmitSearchQuery,
        )
        Spacer(Modifier.height(20.dp))


        EventBriefs(
            events = eventsState.events,
            isLoading = eventsState.isLoading,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        )
    }
}