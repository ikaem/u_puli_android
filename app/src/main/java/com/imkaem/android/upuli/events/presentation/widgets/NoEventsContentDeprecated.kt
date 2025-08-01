package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imkaem.android.upuli.ui.theme.ColorGrey10
import com.imkaem.android.upuli.ui.theme.ColorGrey100
import com.imkaem.android.upuli.ui.theme.ColorGrey30

@Composable
fun NoEventsContentDeprecated(
    modifier: Modifier = Modifier,
    colorVariant: NoEventsContentColorVariant = NoEventsContentColorVariant.DEFAULT,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            "Ništa se ne događa... :(",
            fontSize = 12.sp,
            modifier = Modifier.padding(10.dp),
            color = colorVariant.color,
            textAlign = TextAlign.Center
        )
    }
}

enum class NoEventsContentColorVariant(val color: Color) {
    LIGHT(ColorGrey10),
    DEFAULT(ColorGrey30),
    DARK(ColorGrey100);
}