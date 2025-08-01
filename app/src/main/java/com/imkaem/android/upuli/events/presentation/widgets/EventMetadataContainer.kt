package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.ui.theme.ColorBlue10
import com.imkaem.android.upuli.ui.theme.ColorGrey10
import com.imkaem.android.upuli.ui.theme.ColorGreyBlue10
import com.imkaem.android.upuli.ui.theme.ColorGreyBlue30
import com.imkaem.android.upuli.ui.theme.ColorGreyBlue60
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen10
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen30
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen60

/* TODO not sure if MetadataContainer is the best name */
/* TODO this is deprecated - use EventMetadata*/
@Composable
fun EventDetailsMetadataContainer(
    text: String,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
//    textModifier: Modifier = Modifier,
    textMaxLines: Int = Int.MAX_VALUE,
    textOverflow: TextOverflow = TextOverflow.Clip,
) {

//    EventMetadataContainer(
//        text = text,
//        textColor = ColorGreyBlue30,
//        textSize = 14.sp,
//        iconImageVector = iconImageVector,
//        iconContentDescription = iconContentDescription,
//        iconColor = ColorGreyBlue60,
//        iconSize = 20.dp,
//        containerBackgroundColor = ColorGreyBlue10,
//        textMaxLines = textMaxLines,
//        textOverflow = textOverflow,
//        modifier = modifier,
//    )

    EventMetadataContainer(
        text = text,
        textColor = ColorGreyGreen10,
        textSize = 12.sp,
        iconImageVector = iconImageVector,
        iconContentDescription = iconContentDescription,
        iconColor = ColorGreyBlue60,
        iconSize = 18.dp,
        containerBackgroundColor = ColorGreyGreen60,
        modifier = modifier,
//        textModifier = textModifier,
        textMaxLines = textMaxLines,
        textOverflow = textOverflow,
    )

}

@Composable
fun EventBriefMetadataContainer(
    text: String,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
//    textModifier: Modifier = Modifier,
    textMaxLines: Int = Int.MAX_VALUE,
    textOverflow: TextOverflow = TextOverflow.Clip,
) {

    EventMetadataContainer(
        text = text,
        textColor = ColorGreyGreen30,
        textSize = 12.sp,
        iconImageVector = iconImageVector,
        iconContentDescription = iconContentDescription,
        iconColor = ColorGreyBlue60,
        iconSize = 18.dp,
        containerBackgroundColor = ColorGreyBlue10,
        modifier = modifier,
        textMaxLines = textMaxLines,
        textOverflow = textOverflow,
    )


}

@Composable
fun TodayEventMetadataContainer(
    text: String,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
//    textModifier: Modifier = Modifier,
    textMaxLines: Int = Int.MAX_VALUE,
    textOverflow: TextOverflow = TextOverflow.Clip,
) {

    EventMetadataContainer(
        text = text,
        textColor = ColorGreyGreen10,
        textSize = 12.sp,
        iconImageVector = iconImageVector,
        iconContentDescription = iconContentDescription,
        iconColor = ColorGreyBlue60,
        iconSize = 18.dp,
        containerBackgroundColor = ColorGreyGreen60,
        modifier = modifier,
//        textModifier = textModifier,
        textMaxLines = textMaxLines,
        textOverflow = textOverflow,
    )

}


@Composable
fun TomorrowEventMetadataContainer(
    text: String,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
//    textModifier: Modifier = Modifier,
    textMaxLines: Int = Int.MAX_VALUE,
    textOverflow: TextOverflow = TextOverflow.Clip,
) {

    EventMetadataContainer(
        text = text,
        textColor = ColorGrey10,
        textSize = 10.sp,
        iconImageVector = iconImageVector,
        iconContentDescription = iconContentDescription,
        iconColor = ColorGreyBlue60,
        iconSize = 16.dp,
        containerBackgroundColor = ColorBlue10,
        modifier = modifier,
//        textModifier = textModifier,
        textMaxLines = textMaxLines,
        textOverflow = textOverflow,
    )

}


@Composable
private fun EventMetadataContainer(
    text: String,
    textColor: Color,
    textSize: TextUnit,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    iconColor: Color,
    iconSize: Dp,
    containerBackgroundColor: Color,
    modifier: Modifier,
//    textModifier: Modifier,
    textMaxLines: Int,
    textOverflow: TextOverflow,
) {

    Row(
//        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        /* TODO this kind of label should be unified */
        /* TODO and then maybe we can have sub composables, that use this original composable */
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(
//                    Color.Gray
//                ColorBlue10,
                containerBackgroundColor,
            )
            .padding(horizontal = 5.dp)
    ) {

        Icon(
            imageVector = iconImageVector,
            contentDescription = iconContentDescription,
            tint = iconColor,
            modifier = Modifier
                .size(iconSize)
                .padding(end = 5.dp, top = 5.dp)
        )
        Text(
            text,
            color = textColor,
            fontSize = textSize,
            /* TODO it seems it is not needed to pass weight on the text itself - the row wrapper  is fine*/
//            modifier = textModifier,
            maxLines = textMaxLines,
            overflow = textOverflow,
        )
    }

}