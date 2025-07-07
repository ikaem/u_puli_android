package com.imkaem.android.upuli.events.presentation.widgets.bookmarked_events_screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.presentation.view_models.BookmarkedEventsScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventsContent
import com.imkaem.android.upuli.events.presentation.widgets.EventsContentTitle
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorBlue10
import com.imkaem.android.upuli.ui.theme.ColorBlue60
import com.imkaem.android.upuli.ui.theme.ColorGrey60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen100
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60

@Composable
fun BookmarkedEventsScreenContent(
    eventsState: BookmarkedEventsScreenState,
    fromDateString: String,
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
        modifier = modifier.padding(padding),
        verticalArrangement = Arrangement.Center,
    ) {

        /* TODO this should be extracted */
        Column(
            modifier = Modifier
                .background(ColorBlue60)
                .padding(all = 10.dp)
                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center
//            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(
//                    Color.Gray
//                ColorBlue10,
//                        containerBackgroundColor,
                        ColorBlue10,
                    )
                    .padding(horizontal = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "From Date filter Icon",
                    modifier = Modifier.size(12.dp),
                    tint = ColorGreyGreen60,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 1.dp)
                ) {
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Od: ",
                        fontSize = 12.sp,
                        color = ColorGreyGreen100,
                    )
                    Text(
                        fromDateString,
                        fontSize = 12.sp,
                        color = ColorGreyGreen100,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

        }
        EventsContent(
            events = eventsState.bookmarkedEvents,
            onNavigateToEvent = onNavigateToEvent,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            title = {
                EventsContentTitle(
                    title = "SPREMLJENI DOGAĐAJI",
                    markerColor = ColorGreyGreen60,
                    textColor = ColorGrey60,
                    bottomSpacing = 5.dp,
                )
//                Text(
//                    text = "SPREMLJENI DOGAĐAJI",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                )
            },
        )
//        BookmarkedEventsScreenEventsContent(
//            events = eventsState.bookmarkedEvents,
//            onNavigateToEvent = onNavigateToEvent,
//            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
//        )
    }

}