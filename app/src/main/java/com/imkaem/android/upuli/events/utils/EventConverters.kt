package com.imkaem.android.upuli.events.utils

import android.util.Log
import com.imkaem.android.upuli.events.data.local.EventLocalEntity
import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.extensions.toYearMonth
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventConverters {
    companion object {

        fun localEntityFromRemoteEntity(
            remoteEntity: EventRemoteEntity
        ): EventLocalEntity {
            return EventLocalEntity(
                id = remoteEntity.id,
                title = remoteEntity.title,
                location = remoteEntity.location,
                dateInMilliseconds = remoteEntity.dateInMilliseconds,
                url = remoteEntity.url,
                imageUrl = remoteEntity.imageUrl,
                description = remoteEntity.description
            )
        }

        fun modelFromLocalEntity(
            localEntity: EventLocalEntity
        ): EventModel {
            val instant = Instant.ofEpochMilli(localEntity.dateInMilliseconds)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Zagreb"))

            val model = EventModel(
                id = localEntity.id,
                title = localEntity.title,
                location = localEntity.location,
                dateTime = dateTime,
                url = localEntity.url,
                imageUrl = localEntity.imageUrl,
                description = localEntity.description,
                isBookmarked = localEntity.isBookmarked,
            )

            return model

        }
    }
}