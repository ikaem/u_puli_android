package com.imkaem.android.upuli.events.data.local

import com.imkaem.android.upuli.events.data.database.EventsDao

class EventsLocalDataSource(
    private val dao: EventsDao
) {

    suspend fun getAll(): List<EventLocalEntity> {
        return dao.getAll()
    }

    suspend fun getOne(id: Int): EventLocalEntity? {
        return dao.getOne(id)
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
        eventId: Int,
        isBookmarked: Boolean,
    ) {
        val partial = EventLocalEntityPartialIsBookmarked(
            id = eventId,
            isBookmarked = isBookmarked,
        )

        dao.updateIsBookmarked(partial)
    }


}