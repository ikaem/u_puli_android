package com.imkaem.android.upuli.events.utils

import android.util.Log
import com.imkaem.android.upuli.events.data.local.EventLocalEntity
import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.domain.models.EventModel
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

            val dateFormatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

            val instant = Instant.ofEpochMilli(localEntity.dateInMilliseconds)



            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            /* TODO we should use this i guess */
            /* TODO lets leave this here for now to know how to generate date in different timezone */
//            val zoneInCroatia = ZoneId.of("Europe/Zagreb")
//            Log.d("EventConverters", "Zone in Croatia: $zoneInCroatia")
//            /* TODO just testing this to see if using UTC here will provide correct time - but we should not do that because we are not in UTC */
////            val dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
////            val dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.of("+03:00"))
//
//            val dateTime = LocalDateTime.ofInstant(instant, zoneInCroatia)

            val formattedDate = dateTime.format(dateFormatter)
            val formattedTime = dateTime.format(timeFormatter)

            val model = EventModel(
                id = localEntity.id,
                title = localEntity.title,
                location = localEntity.location,
//                date = formattedDate,
//                time = formattedTime,
                dateTime = dateTime,
                url = localEntity.url,
                imageUrl = localEntity.imageUrl,
                description = localEntity.description,
                isBookmarked = localEntity.isBookmarked,
            )

            return model

        }

        /* TODO not used */
//        fun modelFromRemoteEntity(
//           remoteEntity: EventRemoteEntity
//        ): EventModel {
//            val dateFormatter =
//                DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
//            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
//
//            val instant = Instant.ofEpochMilli(remoteEntity.dateInMilliseconds)
//            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//
//            val formattedDate = dateTime.format(dateFormatter)
//            val formattedTime = dateTime.format(timeFormatter)
//
//            val model = EventModel(
//                id = remoteEntity.id,
//                title = remoteEntity.title,
//                location = remoteEntity.location,
//                date = formattedDate,
//                time = formattedTime,
//                url = remoteEntity.url,
//                imageUrl = remoteEntity.imageUrl,
//                description = remoteEntity.description,
//                isBookmarked = false,
//            )
//
//            return model
//        }
    }
}