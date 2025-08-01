package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.ui.theme.ColorBrownDark
import com.imkaem.android.upuli.ui.theme.ColorTextWhite

/**
 * Use only for screens that display a list of items.
 * */
@Composable
fun ListScreenTitle(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(ColorBrownDark)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Screen title icon",
            tint = ColorTextWhite,
            modifier = Modifier
                .padding(end = 8.dp)
                .width(16.dp)
        )
        Text(
            text = title,
            color = ColorTextWhite,
            fontSize = 14.sp,
        )
    }

}