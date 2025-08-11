package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.format.DateTimeFormatter

fun EventModel.date(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
    return dateTime.format(dateFormatter)
}

fun EventModel.time(): String {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return dateTime.format(timeFormatter)
}