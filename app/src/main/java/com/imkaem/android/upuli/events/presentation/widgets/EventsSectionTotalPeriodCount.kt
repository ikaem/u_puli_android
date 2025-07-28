package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.ui.theme.ColorBlue60

@Composable
fun EventsSectionTotalPeriodCount(
    count: Int,
    onNavigateToPeriodEvents: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            onNavigateToPeriodEvents()
        }
    ) {
        Text(
            "Ukupno $count događaja",
            fontSize = 12.sp,
//            color = ColorBlue60,
            color = color,

            style = TextStyle(
                textDecoration = TextDecoration.Underline
            )
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
            contentDescription = "More today events",
//            tint = ColorBlue60,
            tint = color,
            modifier = Modifier
                .size(20.dp)
                .padding(start = 5.dp)
        )
    }
}