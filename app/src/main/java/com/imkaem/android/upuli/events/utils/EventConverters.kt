package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventConverters {
    companion object {

        fun localEntityFromRemoteEntity() {
            throw NotImplementedError("This method is not implemented yet.")
        }

        fun modelFromLocalEntity(): EventModel {
            throw NotImplementedError("This method is not implemented yet.")
        }

        fun modelFromRemoteEntity(
            entity: EventRemoteEntity
        ): EventModel {
            val dateFormatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

            val instant = Instant.ofEpochMilli(entity.dateInMilliseconds)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            val formattedDate = dateTime.format(dateFormatter)
            val formattedTime = dateTime.format(timeFormatter)

            val model = EventModel(
                id = entity.id,
                title = entity.title,
                location = entity.location,
                date = formattedDate,
                time = formattedTime
            )

            return model
        }
    }
}