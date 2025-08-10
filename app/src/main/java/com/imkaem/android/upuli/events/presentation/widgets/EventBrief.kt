package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
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
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun EventBrief(
    event: EventModel,
    onNavigateToEvent: (id: Int) -> Unit,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorGreenLight)
            .height(110.dp)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = event.imageUrl,
                contentDescription = "Event image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
        Spacer(Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f).clickable {
                        onNavigateToEvent(event.id)
                    }
                )
                Spacer(Modifier.width(10.dp))
                Icon(
                    imageVector = when (event.isBookmarked) {
                        true -> Icons.Filled.Bookmark
                        false -> Icons.Filled.BookmarkBorder
                    },
                    contentDescription = "Bookmark",
                    tint = ColorGreyPink100,
                    modifier = Modifier
                        .width(20.dp)
                        .clickable {
                            onToggleEventIsBookmarked(event.id)
                        }
                )
            }
            Spacer(Modifier.height(5.dp))
            ColoredLabel(
                text = event.location,
                colorVariant = UIElementColorVariant.GREEN,
                iconImageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
            )
            Spacer(Modifier.height(3.dp))
            Row {
                ColoredLabel(
                    text = event.date(),
                    colorVariant = UIElementColorVariant.GREEN,
                    iconImageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Event date",
                )
                Spacer(Modifier.width(3.dp))
                ColoredLabel(
                    text = event.time(),
                    colorVariant = UIElementColorVariant.GREEN,
                    iconImageVector = Icons.Filled.AccessTime,
                    contentDescription = "Event time",
                )
            }

        }
    }
}