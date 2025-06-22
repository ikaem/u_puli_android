package com.imkaem.android.upuli.events.presentation.screens

import EventScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    onNavigateBack: () -> Unit,
) {
    val viewModel: EventViewModel = viewModel()
    val eventState = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack
            )
        },
        content = { padding ->
            EventScreenContent(padding = padding, eventState = eventState)
        }
    )
}

@Composable
@Preview(showBackground = true)
fun EventScreenPreview() {
    EventScreen(
        onNavigateBack = {}
    )
}