package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.events.utils.constants.UIElementColorVariant
import com.imkaem.android.upuli.ui.theme.ColorTextDark
import com.imkaem.android.upuli.ui.theme.ColorGreenDark
import com.imkaem.android.upuli.ui.theme.ColorTextWhite
import com.imkaem.android.upuli.ui.theme.ColorYellowDark

//@Composable
//fun EventMetadataLabelGreen(
//    text: String,
//    iconImageVector: ImageVector,
//    contentDescription: String,
//    modifier: Modifier = Modifier,
//) {
//    EventMetadataLabel(
//        text = text,
//        iconImageVector = iconImageVector,
//        labelColor = ColorGreenDark,
//        contentColor = ColorTextWhite,
//        contentDescription = contentDescription,
//        modifier = modifier,
//    )
//}
//
//@Composable
//fun EventMetadataLabelYellow(
//    text: String,
//    iconImageVector: ImageVector,
//    contentDescription: String,
//    modifier: Modifier = Modifier,
//) {
//    EventMetadataLabel(
//        text = text,
//        iconImageVector = iconImageVector,
//        labelColor = ColorYellowDark,
//        contentColor = ColorTextDark,
//        contentDescription = contentDescription,
//        modifier = modifier,
//    )
//}


/*TODO maybe should creat some wrapper composables for location, date, and time? */
@Composable
fun ColoredLabel(
    text: String,
    colorVariant: UIElementColorVariant,
    iconImageVector: ImageVector,
//    labelColor: Color,
//    contentColor: Color,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    val labelColor = when (colorVariant) {
        UIElementColorVariant.GREEN -> ColorGreenDark
        UIElementColorVariant.YELLOW -> ColorYellowDark
    }

    val contentColor = when (colorVariant) {
        UIElementColorVariant.GREEN -> ColorTextWhite
        UIElementColorVariant.YELLOW -> ColorTextDark
    }


    Row(
        modifier = modifier
            .background(labelColor)
            .padding(
                vertical = 3.dp, horizontal = 5.dp,
            ),
    ) {
        Icon(
            imageVector = iconImageVector,
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = Modifier
                .width(20.dp)
                .padding(end = 5.dp, top = 4.dp)
        )
        Text(
            text = text,
            color = contentColor,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}