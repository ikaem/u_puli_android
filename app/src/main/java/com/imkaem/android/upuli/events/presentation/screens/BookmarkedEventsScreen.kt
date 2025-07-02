package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState

@Composable
fun BookmarkedEventsScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            UPuliTopAppBar(
                title = "Spremljeni događaji",
                onNavigateBack = onNavigateBack,
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text("Bookmarked Events")
        }
    }


}

/* TODO should be moved to a separate file */
@Composable
fun BookmarkedEventsScreenContent(
    eventsState: BookmarkedEventsScreenState,
    onNavigateToEvent: (id: Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

}

/* TODO should be moved to same file as the above one */
@Composable
fun BookmarkedEventsScreenNoEventsContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            "Ne postoji nijedan spremljeni događaj." +
                    "\n" +
                    "Spremite jedan događaj da bi ga vidjeli ovdje",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp)

        )
    }
}