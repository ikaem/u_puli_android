package com.imkaem.android.upuli.events.data.local

import android.media.metrics.Event
import com.imkaem.android.upuli.events.data.database.EventsDao
import com.imkaem.android.upuli.events.utils.values.UpdateEventLocalIsBookmarkedValue

class EventsLocalDataSource(
    private val dao: EventsDao
) {

    suspend fun getAll(): List<EventLocalEntity> {
        return dao.getAll()
    }

    suspend fun getOne(id: Int): EventLocalEntity? {
        return dao.getOne(id)
    }

    suspend fun getAllBookmarked(): List<EventLocalEntity> {
        return dao.getAllBookmarked()
    }

    suspend fun getAllBookmarkedFromInclusive(
        fromMillisecondsInclusive: Long
    ): List<EventLocalEntity> {
        return dao.getAllBookmarkedFromInclusive(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
        )
    }

    suspend fun getAllFromInclusive(
        fromMillisecondsInclusive: Long,
    ): List<EventLocalEntity> {
        return dao.getAllFromInclusive(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
        )
    }

    suspend fun getAllToExclusive(
        toMillisecondsExclusive: Long,
    ): List<EventLocalEntity> {
        return dao.getAllToExclusive(
            toMillisecondsExclusive = toMillisecondsExclusive,
        )
    }

    suspend fun getAllFromInclusiveToExclusive(
        fromMillisecondsInclusive: Long,
        toMillisecondsExclusive: Long,
    ): List<EventLocalEntity> {
        return dao.getAllFromInclusiveToExclusive(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
            toMillisecondsExclusive = toMillisecondsExclusive,
        )
    }

    suspend fun addAll(events: List<EventLocalEntity>) {
        dao.addAll(events)
    }

    suspend fun add(event: EventLocalEntity) {
        dao.add(event)
    }

    /* TODO: normally, i would use some custom wrapper that looks like entity, so if i every chnage lib, i can still depend on my on class */
    suspend fun updateEventIsBookmarked(
//        eventId: Int,
//        isBookmarked: Boolean,
        updateValue: UpdateEventLocalIsBookmarkedValue
    ) {
        val partial = EventLocalEntityPartialIsBookmarked(
            id = updateValue.eventId,
            isBookmarked = updateValue.isBookmarked,
        )

        dao.updateIsBookmarked(partial)
    }

    suspend fun updateEventsIsBookmarked(
        updateValues: List<UpdateEventLocalIsBookmarkedValue>
    ) {
        val partials = updateValues.map { updateValue ->
            EventLocalEntityPartialIsBookmarked(
                id = updateValue.eventId,
                isBookmarked = updateValue.isBookmarked,
            )
        }

        dao.updateAllIsBookmarked(partials)
    }
}