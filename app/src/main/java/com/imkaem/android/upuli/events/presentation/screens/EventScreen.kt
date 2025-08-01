package com.imkaem.android.upuli.events.presentation.screens

import EventScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.events.domain.models.date
import com.imkaem.android.upuli.events.domain.models.time
import com.imkaem.android.upuli.events.presentation.view_models.EventScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.ColoredLabel
import com.imkaem.android.upuli.events.presentation.widgets.EventBriefMetadataContainer
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToEventWebView: (id: Int) -> Unit,

    ) {
    val viewModel: EventScreenViewModel = viewModel()
    val eventState = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },
    ) { padding ->

        /* TODO from here should be extracted to EventScreenContent*/

        EventScreenContent(
            padding = padding,
            eventState = eventState,
            onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
            onNavigateToEventWebView = onNavigateToEventWebView,
        )




    }
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