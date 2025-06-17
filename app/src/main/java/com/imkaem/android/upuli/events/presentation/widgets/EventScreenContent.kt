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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun EventScreenContent(
    padding: PaddingValues,
    event: EventModel?,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
//            .fillMaxSize()
            .padding(padding),
    ) {

        if (event == null) {
            NoEventContent(
//                padding = padding,
                modifier = modifier
            )
            return
        }

        EventContent(
//            padding = padding,
            event = event,
            modifier = modifier
        )
    }
}

@Composable
private fun EventContent(
    event: EventModel,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(all = 10.dp).verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                "Some title that sometimes might be too long yes it is very long",
                overflow = TextOverflow.Visible,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
            )
            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Toggle favorite",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                event.location,
                fontSize = 14.sp,
                )
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "Location",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp),
            )
        }
        Spacer(Modifier.height(2.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                SimpleDateFormat(
                    "dd.MM.yyyy.",
                    Locale.getDefault()
                ).format(event.date),
                fontSize = 14.sp,
            )
            Icon(
                Icons.Filled.CalendarMonth,
                contentDescription = "Date",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp),
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                SimpleDateFormat(
                    "HH:mm",
                    Locale.getDefault()
                ).format(event.date),
                fontSize = 14.sp,
            )
            Icon(
                Icons.Filled.AccessTime,
                contentDescription = "Time",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp),
            )

        }
        Spacer(Modifier.height(15.dp))
        AsyncImage(
            "https://picsum.photos/1000/1000",
            contentDescription = "Event image",
        )
        Spacer(Modifier.height(20.dp))
        Text(
            "Na Svjetski dan glazbe - 21. lipnja 2025. - na Odjelu za djecu i mlade Središnje knjižnice čitamo i stvaramo uz slikovnicu Kako je Imra pomirila instrumente.Ovom edukativno-kreativnom radionicom približit ćemo glazbu najmlađima, poticati toleranciju, različitost i, iznad svega, ljubav prema glazbi.Glavnu junakinju, Imru, glazba nije zanimala sve do jednog zanimljivog sna o glazbi i instrumentima. Pridružite nams e i osjetite moć zajedništva, naučite ponešto o različitim instrumentima i pokažite vlastite glazbene interese.",
        )
    }

}

@Composable
private fun NoEventContent(
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