package com.imkaem.android.upuli.events.presentation.widgets.home_screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.domain.models.date
import com.imkaem.android.upuli.events.domain.models.time
import com.imkaem.android.upuli.events.presentation.widgets.ColoredLabel
import com.imkaem.android.upuli.events.presentation.widgets.EventBrief
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun EventBriefs(
    events: List<EventModel>,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    if (events.isEmpty()) {
        NoEventsContent(
            colorVariant = UIElementColorVariant.GREEN,
        )
        return
    }

    // TODO this should be replaced with a list of events
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            count = events.size,
            key = { index -> events[index].id }
        ) { index ->
            val event = events[index]

            EventBrief(
                event = event,
                onNavigateToEvent = onNavigateToEvent,
                onToggleEventIsBookmarked = onToggleEventIsBookmarked,
            )

            val isLastItem = index == events.lastIndex
            if (isLastItem) return@items

            Spacer(Modifier.height(5.dp))
            HorizontalDivider(
                color = ColorYellowLight
            )
            Spacer(Modifier.height(5.dp))

        }
    }

}