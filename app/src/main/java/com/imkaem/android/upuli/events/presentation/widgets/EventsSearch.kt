package com.imkaem.android.upuli.events.presentation.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.ui.theme.ColorYellowDark
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@Composable
fun EventsSearch() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = ColorYellowLight,
            focusedContainerColor = ColorYellowLight,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                tint = ColorYellowDark,
                modifier = Modifier
                    .size(24.dp),
                contentDescription = "Search"
            )
        }
    )
}