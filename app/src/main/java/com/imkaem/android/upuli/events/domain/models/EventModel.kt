package com.imkaem.android.upuli.events.domain.models

import androidx.core.net.toUri
import coil3.Uri
import java.net.URI
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class EventModel(
    val id: Int,
    val title: String,
    val location: String,
//    val date: LocalDateTime,
//    val date: String,
//    val time: String,
    val dateTime: LocalDateTime,
    val url: String,
    val imageUrl: String,
    val description: String,
    val isBookmarked: Boolean,
)


/* TODO move to extension folders, or something */
fun EventModel.date(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
    return dateTime.format(dateFormatter)
}

fun EventModel.time(): String {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return dateTime.format(timeFormatter)
}