package com.imkaem.android.upuli.events.domain.models

import java.util.Date

data class EventModel(
    val id: Int,
    val title: String,
    val location: String,
    val date: Date,
)
