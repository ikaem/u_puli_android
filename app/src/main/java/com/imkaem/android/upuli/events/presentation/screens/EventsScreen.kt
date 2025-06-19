package com.imkaem.android.upuli.events.presentation.screens

import EventBriefItem
import EventsScreenContent
import UPuliTopAppBar
import android.media.metrics.Event
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventsViewModel
import com.imkaem.android.upuli.events.utils.DummyData
import com.imkaem.android.upuli.ui.theme.UPuliTheme
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

//import java.util.Date

/* TODO: this is probably best to be called HomeScreen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
) {

    val viewModel: EventsViewModel = viewModel()
    val eventsState = viewModel.state.value

    Scaffold(
        /* TODO not sure if this contributes to anything in this particular case */
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar()
        },
        content = { padding ->

            EventsScreenContent(
                eventsState = eventsState,
                onNavigateToEvent = onNavigateToEvent,
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
            onNavigateToEvent = { id -> }
        )
    }
}