package com.imkaem.android.upuli.events.domain.models

import androidx.core.net.toUri
import coil3.Uri
import java.net.URI
import java.net.URL
import java.time.LocalDateTime
import java.util.Date

data class EventModel(
    val id: Int,
    val title: String,
    val location: String,
//    val date: LocalDateTime,
    val date: String,
    val time: String,
    val url: String,
    val imageUrl: String,
    val description: String,
    val isBookmarked: Boolean,
)


