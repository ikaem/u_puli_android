package com.imkaem.android.upuli.events.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.utils.DummyData
import com.imkaem.android.upuli.ui.theme.UPuliTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {

    val events = DummyData.dummyEvents

    Scaffold(
        /* TODO not sure if this contributes to anything in this particular case */
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("U Puli")
                }
            )
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding,
            ) {

                items(
                    count = events.size,
                    key = { index -> events[index].id }
                ) { index ->

                    val event = events[index]

                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {

                        Text(
                            text = event.title,
                        )
                    }

                }

            }

        }

    )
}

@Composable
@Preview(showBackground = true)
fun EventsScreenPreview() {
    UPuliTheme {
        EventsScreen()
    }
}