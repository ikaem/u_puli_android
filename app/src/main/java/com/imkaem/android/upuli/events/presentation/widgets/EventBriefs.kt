package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.events.utils.extensions.monthYearFormat
import com.imkaem.android.upuli.ui.theme.ColorTextWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowDark
import com.imkaem.android.upuli.ui.theme.ColorYellowLight
import java.time.LocalDateTime

@Composable
fun EventBriefs(
    eventsMap: Map<LocalDateTime, List<EventModel>>,
    shouldShowMonthHeaders: Boolean,
    isLoading: Boolean,
    onNavigateToEvent: (Int) -> Unit,
    onToggleEventIsBookmarked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isLoading) {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
        return
    }

    if (eventsMap.isEmpty()) {
        NoEventsContent(
            colorVariant = UIElementColorVariant.GREEN,
        )
        return
    }


    // TODO this should be replaced with a list of events
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        for (date in eventsMap.keys) {
            if (shouldShowMonthHeaders) {
                val month = date.monthYearFormat()
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .background(ColorYellowLight)
                            .padding(10.dp),
                    ) {
                        Text(
                            text = month.uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = ColorTextWhite,
                            modifier = Modifier
                                .background(ColorYellowDark)
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
            }

            val monthEvents = eventsMap[date] ?: emptyList()
            items(
                count = monthEvents.size,
                key = { index -> monthEvents[index].id }
            ) { index ->
                val event = monthEvents[index]
                EventBrief(
                    event = EventModel(
                        id = event.id,
                        title = event.title,
                        location = event.location,
                        dateTime = event.dateTime,
                        imageUrl = event.imageUrl,
                        description = event.description,
                        isBookmarked = event.isBookmarked,
                        url = event.url,
                    ),
                    onNavigateToEvent = onNavigateToEvent,
                    onToggleEventIsBookmarked = onToggleEventIsBookmarked,
                )

                Spacer(Modifier.height(10.dp))
            }
        }
    }

}