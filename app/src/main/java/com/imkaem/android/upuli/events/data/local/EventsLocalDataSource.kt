package com.imkaem.android.upuli.events.data.local

import com.imkaem.android.upuli.events.data.database.EventsDao
import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.utils.values.UpdateEventLocalIsBookmarkedValue
import kotlinx.coroutines.flow.Flow

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

    /* TODO used in old logic without flows */
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

    fun getAllFromInclusiveFlow(
        fromMillisecondsInclusive: Long,
    ): Flow<List<EventLocalEntity>> {
        return dao.getAllFromInclusiveFlow(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
        )
    }

    fun getAllFromInclusiveToExclusiveFlow(
        fromMillisecondsInclusive: Long,
        toMillisecondsExclusive: Long,
    ): Flow<List<EventLocalEntity>> {
        return dao.getAllFromInclusiveToExclusiveFlow(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
            toMillisecondsExclusive = toMillisecondsExclusive,
        )
    }

    fun getAllToExclusiveFlow(
        toMillisecondsExclusive: Long,
    ): Flow<List<EventLocalEntity>> {
        return dao.getAllToExclusiveFlow(
            toMillisecondsExclusive = toMillisecondsExclusive,
        )
    }


    fun getOneFlow(id: Int): Flow<EventLocalEntity?> {
        return dao.getOneFlow(id);
    }


    fun getAllFlow(): Flow<List<EventLocalEntity>> {
        return dao.getAllFlow()
    }

    fun getAllByIdsFlow(ids: List<Int>): Flow<List<EventLocalEntity>> {
        return dao.getAllByIdsFlow(ids)
    }

    /* TODO it seems for now we dont need any other date limits for bookmarked - we only ever show all available from now */
    fun getAllBookmarkedFromInclusiveFlow(
        fromMillisecondsInclusive: Long,
    ): Flow<List<EventLocalEntity>> {
        return dao.getAllBookmarkedFromInclusiveFlow(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
        )
    }
}

/*
*     suspend fun getAllFromInclusive(
        fromMillisecondsInclusive: Long,
    ): List<EventLocalEntity> {
        return dao.getAllFromInclusive(
            fromMillisecondsInclusive = fromMillisecondsInclusive,
        )
    }
*
* */