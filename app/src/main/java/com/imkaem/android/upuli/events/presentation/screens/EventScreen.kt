package com.imkaem.android.upuli.events.presentation.screens

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
    val event = viewModel.state.value

    Scaffold(
        topBar = {
//            TopAppBar(
//            CenterAlignedTopAppBar(
            CenterAlignedTopAppBar(
                title = {
                    Text("Detalji")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to previous screen"
                        )
                    }
                }

            )
        },
        content = { it ->
//            Text(
//                "Event details will be displayed here.",
//                modifier = Modifier.padding(it)
//            )
            EventScreenContent(padding = it, event = event)
        }
    )
}

@Composable
private fun EventScreenContent(
    padding: PaddingValues,
    event: EventModel?,
    modifier: Modifier = Modifier,
) {

    if (event == null) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "There was an issue finding the event you are looking for.",
                textAlign = TextAlign.Center
            )
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(event.title)
        Spacer(Modifier.height(4.dp))
        Text("This is some description")
        Spacer(Modifier.height(8.dp))
        Text(event.location)
        Spacer(Modifier.height(8.dp))
        Text(
            SimpleDateFormat(
                "dd.MM.yyyy. HH:mm",
                Locale.getDefault()
            ).format(event.date)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun EventScreenPreview() {
    EventScreen(
        onNavigateBack = {}
    )
}