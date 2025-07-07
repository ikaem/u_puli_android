package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.ui.theme.ColorGrey10

@Composable
fun EventsContentTitle(
    title: String,
    markerColor: Color,
    textColor: Color = ColorGrey10,
    textSize: TextUnit = 16.sp,
    bottomSpacing: Dp = 15.dp
    ) {

    Text(
        title,
        fontSize = textSize,
        fontWeight = FontWeight.Bold,
        color = textColor,
        modifier = Modifier
            .drawWithCache {
                /* NOTE: i dont even know how this works
            * https://stackoverflow.com/a/74065658/9661910
            * */
                onDrawWithContent {
                    drawLine(
                        color = markerColor,
                        start = Offset.Zero,
                        end = Offset(0f, this.size.height),
                        strokeWidth = 8f
                    )

                    drawContent()
                }
            }
            .padding(start = 7.dp)

//            modifier = Modifier
//                        .border(
//                            BorderStroke(2.dp, SolidColor(Color.Red)),
////                            shape = RoundedCornerShape(10.dp)
//                        ).padding(start = 5.dp)
    )
    Spacer(Modifier.height(bottomSpacing))

}