package com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState

@Composable
fun BookmarkedEventsScreenContent(
    eventsState: BookmarkedEventsScreenState,
    fromDateString: String,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(padding)
    ) {
//        Text(
//            "Ovo je dio s filterima ",
//            modifier = Modifier.padding(10.dp)
//        )
        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.CalendarToday,
                contentDescription = "Filter Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Od: $fromDateString",
                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
            )
//            Text(
//                "DATE: $fromDateString",
//                fontSize = 10.dp,
//            )
        }
        Spacer(Modifier.height(10.dp))
        BookmarkedEventsScreenEventsContent(
            events = eventsState.bookmarkedEvents,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
        )
    }

}