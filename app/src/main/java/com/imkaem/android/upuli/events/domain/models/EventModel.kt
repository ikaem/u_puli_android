package com.imkaem.android.upuli.events.domain.models

import java.time.LocalDateTime
import java.util.Date

data class EventModel(
    val id: Int,
    val title: String,
    val location: String,
//    val date: LocalDateTime,
    val date: String,
    val time: String,
)
