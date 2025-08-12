import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.events.presentation.view_models.EventScreenState
import com.imkaem.android.upuli.events.presentation.widgets.ColoredLabel
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.events.presentation.widgets.NoEventsContent
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.events.utils.extensions.date
import com.imkaem.android.upuli.events.utils.extensions.time
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun EventScreenContent(
    eventState: EventScreenState,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    onNavigateToEventWebView: (id: Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .padding(
                top = padding.calculateTopPadding(),
                start = 10.dp,
                end = 10.dp,
                bottom = padding.calculateBottomPadding(),
            )
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(Modifier.height(10.dp))

        val isLoading = eventState.isLoading;
        if (isLoading) {
            LoadingIndicator(
                modifier = Modifier.fillMaxSize()
            )
            return@Column
        }

        val event = eventState.event
        if (event == null) {
            NoEventsContent(
                colorVariant = UIElementColorVariant.YELLOW
            )
            return@Column
        }

        /* event is now available */

        Column(
            modifier = Modifier
                .background(ColorYellowLight)
                .padding(10.dp)
                .fillMaxWidth()

        ) {

            Column(
            ) {
                AsyncImage(
                    model = event.imageUrl,
                    contentDescription = "Event image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp),
                )
            }

            Spacer(Modifier.height(10.dp))

            Row() {
                Text(
                    text = event.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                Column(
                    modifier = Modifier.clickable {
                        onToggleEventIsBookmarked(event.id)
                    }
                ) {
                    Icon(
                        imageVector = when (event.isBookmarked) {
                            true -> Icons.Filled.Bookmark
                            false -> Icons.Filled.BookmarkBorder
                        },
                        contentDescription = "Bookmark",
                        tint = ColorGreyPink100,
                        modifier = Modifier.size(24.dp)
                    )
                }

            }

            Spacer(Modifier.height(10.dp))
            ColoredLabel(
                text = event.location,
                colorVariant = UIElementColorVariant.YELLOW,
                iconImageVector = Icons.Filled.LocationOn,
                contentDescription = "Venue location",
            )
            Spacer(Modifier.height(5.dp))
            Row {
                ColoredLabel(
                    text = event.date(),
                    colorVariant = UIElementColorVariant.YELLOW,
                    iconImageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Event date",
                )
                Spacer(modifier = Modifier.width(5.dp))
                ColoredLabel(
                    text = event.time(),
                    colorVariant = UIElementColorVariant.YELLOW,
                    iconImageVector = Icons.Filled.AccessTime,
                    contentDescription = "Event time",
                )
            }
            Spacer(Modifier.height(10.dp))
            ColoredLabel(
                text = "Više informacija",
                colorVariant = UIElementColorVariant.GREEN,
                iconImageVector = Icons.Filled.Link,
                contentDescription = "Event link",
                modifier = Modifier.clickable {
                    onNavigateToEventWebView(event.id)
                }
            )

            if (event.description.isBlank()) {
                return@Column
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                thickness = 1.dp,
                color = ColorWhite,
            )

            Text(
                text = event.description,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = ColorTextDark,
            )
        }

    }
}


