import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun EventBriefItem(
    event: EventModel,
    onNavigateToEvent: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {



    /* TODO this should be done in view model - all UI stuff should be already preparred - maybe as an */
    val dateFormatter = SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault())
    val formattedDate = dateFormatter.format(event.date)

    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = timeFormatter.format(event.date)

    Column(
        modifier = modifier.clickable {
            onNavigateToEvent(event.id)
        }
    ) {

        Text(
            text = event.title,
                fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = event.location,
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(20.dp).padding(start = 5.dp)
                )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Text(
                text = formattedDate,
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Date",
                modifier = Modifier.size(20.dp).padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = formattedTime,
                fontSize = 14.sp,
            )
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Time",
                modifier = Modifier.size(20.dp).padding(start = 5.dp)
            )

        }
    }

//    }
}
