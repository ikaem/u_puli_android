package com.imkaem.android.upuli.events.presentation.widgets.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.R
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.domain.models.date
import com.imkaem.android.upuli.events.domain.models.time
import com.imkaem.android.upuli.events.presentation.widgets.ColoredLabel
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorGreenDark
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorTextLight
import com.imkaem.android.upuli.ui.theme.ColorWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowDark
import com.imkaem.android.upuli.ui.theme.ColorYellowLight


@Composable
fun SoonEventsCard(
    colorVariant: UIElementColorVariant,
    cardTitle: String,
    cardDate: String,
    featuredEvent: EventModel?,
    totalSoonEvents: Int,
    allSoonEventsString: String,
    onNavigateToEvent: (Int) -> Unit,
    onNavigateToPeriodEvents: () -> Unit,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val darkColor = when (colorVariant) {
        UIElementColorVariant.YELLOW -> ColorYellowDark
        UIElementColorVariant.GREEN -> ColorGreenDark
    }

    val lightColor = when (colorVariant) {
        UIElementColorVariant.YELLOW -> ColorYellowLight
        UIElementColorVariant.GREEN -> ColorGreenLight
    }

    val textColor = when (colorVariant) {
        UIElementColorVariant.YELLOW -> ColorTextDark
        UIElementColorVariant.GREEN -> ColorTextLight
    }


    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.drawWithCache {
                onDrawWithContent {
                    drawLine(
                        color = darkColor,
                        start = Offset(5f, 0f),
                        end = Offset(5f, this.size.height),
                        strokeWidth = 8f
                    )
                    drawContent()
                }
            }
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = cardTitle,
                color = ColorTextDark,
                fontWeight = FontWeight.Bold,


                )
            Spacer(Modifier.width(5.dp))
            Text(
                text = cardDate,
                color = ColorTextLight,
                fontWeight = FontWeight.Bold,

                )
        }
        Spacer(Modifier.height(10.dp))

        if (featuredEvent == null) {
            NoEventsContent(
                colorVariant,
            )
            return@Column
        }

        /* we could wrap everything in Elevated card to achieve shadow
        * https://stackoverflow.com/a/79062345/9661910
        * */

        /*or we can use zindex
        * https://stackoverflow.com/a/73025691/9661910
        * */

        /*or use surface
        * https://stackoverflow.com/a/71959615/9661910
        *
        * */


        Column(
            modifier = Modifier
                .background(lightColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.35f)
                        .heightIn(0.dp, 200.dp)
                ) {
                    AsyncImage(
                        model = featuredEvent.imageUrl,
                        contentDescription = "Event image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxHeight(),
                        error = painterResource(R.drawable.event)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(0.65f)

                ) {
                    Text(
                        text = featuredEvent.title,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        modifier = modifier.clickable {
                            onNavigateToEvent(featuredEvent.id)
                        }
                    )
                    Spacer(Modifier.height(10.dp))

                    ColoredLabel(
                        text = featuredEvent.location,
                        colorVariant = colorVariant,
                        iconImageVector = Icons.Filled.LocationOn,
                        contentDescription = "Venue location",
                    )
                    Spacer(Modifier.height(5.dp))
                    ColoredLabel(
                        text = featuredEvent.date(),
                        colorVariant = colorVariant,
                        iconImageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Event date",
                    )
                    Spacer(Modifier.height(5.dp))
                    ColoredLabel(
                        text = featuredEvent.time(),
                        colorVariant = colorVariant,
                        iconImageVector = Icons.Filled.AccessTime,
                        contentDescription = "Event time",
                    )
                    Spacer(Modifier.height(10.dp))
                    Icon(
                        modifier = Modifier.clickable {
                            onToggleEventIsBookmarked(featuredEvent.id)
                        },
                        imageVector = when (featuredEvent.isBookmarked) {
                            true -> Icons.Filled.Bookmark
                            false -> Icons.Filled.BookmarkBorder
                        },
                        contentDescription = "Bookmark event",
                        tint = ColorGreyPink100,
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                thickness = 2.dp,
                color = ColorWhite,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        onNavigateToPeriodEvents()
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "UKUPNO $totalSoonEvents DOGAĐAJA",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        )

                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                        contentDescription = "More events",
                        modifier = Modifier
                            .width(22.dp)
                            .padding(start = 5.dp)
                            .clickable {}
                    )
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    text = allSoonEventsString,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }


}