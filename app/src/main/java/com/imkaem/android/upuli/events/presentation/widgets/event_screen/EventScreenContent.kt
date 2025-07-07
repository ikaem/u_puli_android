import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.R
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.presentation.view_models.EventScreenState
import com.imkaem.android.upuli.events.presentation.widgets.EventDetailsMetadataContainer
import com.imkaem.android.upuli.ui.theme.ColorGrey10
import com.imkaem.android.upuli.ui.theme.ColorGrey5
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100
import com.imkaem.android.upuli.ui.theme.ColorWhite
import java.nio.file.WatchEvent
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun EventScreenContent(
    eventState: EventScreenState,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val event = eventState.event

    Column(
        modifier = modifier
            .padding(padding)
    ) {

        if (event == null) {
            EventScreenNoEventContent()
            return
        }

        EventScreenEventContent(
            event = event,
            onToggleEventIsBookmarked = onToggleEventIsBookmarked,

            )
    }
}


@Composable
private fun EventScreenEventContent(
    event: EventModel,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
//            .padding(all = 10.dp)
            .padding(vertical = 10.dp)
            .verticalScroll(rememberScrollState())
//            .fillMaxWidth()
    ) {

        /* TODO this might do with some widgets extraction */
        Column(
            modifier = Modifier.padding(horizontal = 10.dp,)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    event.title,
                    overflow = TextOverflow.Visible,
                    color = ColorWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
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
                        tint = ColorGreyPink100,
                        contentDescription = "Toggle favorite",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            EventDetailsMetadataContainer(
//            text = "event.location asd asd asf sdf sdg dsfg sd asd asdasdasfgasf sdfd sdf sd",
                text = event.location,
                iconImageVector = Icons.Filled.LocationOn,
                iconContentDescription = "Venue location",
            )
            Spacer(Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                EventDetailsMetadataContainer(
                    text = event.date,
                    iconImageVector = Icons.Filled.CalendarMonth,
                    iconContentDescription = "Event date",
                )
                Spacer(Modifier.width(5.dp))
                EventDetailsMetadataContainer(
                    text = event.time,
                    iconImageVector = Icons.Filled.AccessTime,
                    iconContentDescription = "Event time",
                )
            }
        }

        Spacer(Modifier.height(15.dp))
        Column(
//            modifier = Modifier.background(ColorWhite)
        ) {
            AsyncImage(
                //            "https://picsum.photos/1000/1000",
                event.imageUrl,
                contentDescription = "Event image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                error = painterResource(R.drawable.event)
            )
//            Spacer(Modifier.height(20.dp))
        }
        if (event.description.trim().isNotEmpty()) {
            Column(
                modifier = Modifier
                    .background(ColorGrey5)
                    .fillMaxWidth()
//                    .let {
//                        if (!event.description.trim().isEmpty()) {
//                            it.padding(10.dp)
//                        } else {
//                            it
//                        }
//                    }
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text(
                    /* TODO trim should have been done on backend*/
                    event.description.trim(),
                    //            color = ColorWhite,
                )
            }
        }
    }

}

@Composable
private fun EventScreenNoEventContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "There was an issue finding the event you are looking for.",
            textAlign = TextAlign.Center
        )
    }

}