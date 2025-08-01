package com.imkaem.android.upuli.events.presentation.screens

import HomeScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExploreOff
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsPaused
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.events.domain.models.date
import com.imkaem.android.upuli.events.domain.models.time
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.ColoredLabel
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.presentation.widgets.home_screen.EventBriefs
import com.imkaem.android.upuli.events.presentation.widgets.home_screen.SoonEventsCard
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorBrownDark
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorTextWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowLight
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