import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EventBriefItem(
    event: EventModel,
    onNavigateToEvent: (id: Int) -> Unit,
    onToggleEventIsBookmarked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {



    /* TODO this should be done in view model - all UI stuff should be already preparred - maybe as an */
//    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
//    val formattedDate = event.date.format(formatter)
//
//    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
//    val formattedTime = event.date.format(timeFormatter)

    Column(
        modifier = modifier.clickable {
            onNavigateToEvent(event.id)
        }
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = event.title,
//                text = "event.title very long title that what is what is what is asd asd asd  ",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
//                TODO maybe this does not need to be visible - maybe we can have one line?
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.clickable {
//                   onBookmark(event.id)
                    onToggleEventIsBookmarked(event.id)
                }
            ) {
                Icon(
                    imageVector = when(event.isBookmarked) {
                        true -> Icons.Filled.Bookmark
                        false ->Icons.Filled.BookmarkBorder
                    },
                    contentDescription = "Bookmark",
                )
            }
            /* TODO not sure how to remove padding around it that i dont want*/
//            IconButton(
//                onClick = {},
//                modifier = Modifier.padding(0.dp)
//
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.BookmarkBorder,
//                    contentDescription = "Bookmark",
//                )
//            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 5.dp)
                )
            Text(
                text = event.location,
//                text = "event.location very long localtion very ver lomng long logn ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                /*TODO in this case weight is not even needed*/
                fontSize = 14.sp,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Date",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 5.dp)
            )
            Text(
                text = event.date,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Time",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 5.dp)
            )
            Text(
                text = event.time,
                fontSize = 14.sp,
            )

        }
    }

//    }
}
