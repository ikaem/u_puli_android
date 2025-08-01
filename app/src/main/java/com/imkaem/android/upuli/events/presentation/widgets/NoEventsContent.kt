package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExploreOff
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorBrownDark
import com.imkaem.android.upuli.ui.theme.ColorGreenLight
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun NoEventsContent(
    colorVariant: UIElementColorVariant,
) {

    val backgroundColor = when (colorVariant) {
        UIElementColorVariant.GREEN -> ColorGreenLight
        UIElementColorVariant.YELLOW -> ColorYellowLight
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(backgroundColor).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Icon(
                imageVector = Icons.Filled.ExploreOff,
                contentDescription = "No events icon",
                tint = ColorBrownDark,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
            )
            Icon(
                imageVector = Icons.Filled.SentimentVeryDissatisfied,
                contentDescription = "No events icon",
                tint = ColorBrownDark,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
            )
        }
        Spacer(Modifier.height(10.dp))
        ColoredLabel(
            text = "Koja tapija...",
            colorVariant = colorVariant,
            iconImageVector = Icons.Filled.ThumbDown,
            contentDescription = "No events message"
        )
        Spacer(Modifier.height(5.dp))
        ColoredLabel(
            text = "Ništa se ne događa...",
            colorVariant = colorVariant,
            iconImageVector = Icons.Filled.Delete,
            contentDescription = "No events message"
        )
    }


}