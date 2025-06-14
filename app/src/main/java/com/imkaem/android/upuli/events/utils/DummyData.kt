package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.domain.models.EventModel
import java.util.Date

object DummyData {
    val dummyEvents = List<EventModel>(100) { index ->
//        val milliseconds = index * 1000
//        val seconds = milliseconds * 60
//        val minutes = seconds * 60
        val oneHourInMilliseconds = 1000 * 60 * 60
        val date = Date(Date().time + oneHourInMilliseconds )

        EventModel(
            id = index + 1,
            title = "Event ${index + 1}",
            location = "Location ${index + 1}",
            date = date,
        )
    }
}