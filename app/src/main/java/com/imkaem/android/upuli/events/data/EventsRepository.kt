package com.imkaem.android.upuli.events.data

import com.imkaem.android.upuli.events.data.local.EventsLocalDataSource
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.EventConverters
import com.imkaem.android.upuli.events.utils.values.UpdateEventLocalIsBookmarkedValue

class EventsRepository(
    private val eventsRemoteDataSource: EventsRemoteDataSource,
    private val eventsLocalDataSource: EventsLocalDataSource,
) {
    /* TODO eventually, this could get filter*/
    suspend fun loadEvents() {
        /* TODO in all of these should probably use withContenxt, and receive dispatcher in a constructor? */
        /* TODO this could be causing issues, because we night be trying to update events that do not exist in db? */
        /* or maybe it cannot, because it will eventually be derived form actual db events */
        val bookmarkedEvents = eventsLocalDataSource.getAllBookmarked()
        val remoteEventEntities = eventsRemoteDataSource.getEvents()

        val localEventEntities = remoteEventEntities.map { it ->
            EventConverters.localEntityFromRemoteEntity(it)
        }

        eventsLocalDataSource.addAll(
            localEventEntities
        )

        /* to make sure that correct events are update as bookmarked */
        val values = bookmarkedEvents.map { it ->
            UpdateEventLocalIsBookmarkedValue(
                eventId = it.id,
                isBookmarked = true,
            )
        }
        eventsLocalDataSource.updateEventsIsBookmarked(
            updateValues = values,
        )

    }

    suspend fun getBookmarkedEventsFromInclusive(
        fromMillisecondsInclusive: Long,
    ): List<EventModel> {

        val localEntities = eventsLocalDataSource.getAllBookmarkedFromInclusive(
            fromMillisecondsInclusive = fromMillisecondsInclusive
        )
        val models = localEntities.map { it ->
            EventConverters.modelFromLocalEntity(it)
        }

        return models
    }

    /* TODO maybe too many functions here, will see */
    suspend fun getEvents(
        /* TODO this should be called GetEventsDateFilter, if we want to keep it all in one function */
        filter: GetEventsFilter,
    ): List<EventModel> {
        val fromInclusive = filter.fromDateMilliseconds;
        val toExclusive = filter.toDateMilliseconds;

        val isFromAndTo = fromInclusive != null && toExclusive != null
        if (isFromAndTo) {
            val localEntities = eventsLocalDataSource.getAllFromInclusiveToExclusive(
                fromMillisecondsInclusive = fromInclusive,
                toMillisecondsExclusive = toExclusive
            )

            val models = localEntities.map { it ->
                EventConverters.modelFromLocalEntity(it)
            }
            return models
        }


        val isFrom = fromInclusive != null
        if (isFrom) {
            val localEntities = eventsLocalDataSource.getAllFromInclusive(
                fromMillisecondsInclusive = fromInclusive
            )

            val models = localEntities.map { it ->
                EventConverters.modelFromLocalEntity(it)
            }
            return models
        }

        val isTo = toExclusive != null
        if (isTo) {
            val localEntities = eventsLocalDataSource.getAllToExclusive(
                toMillisecondsExclusive = toExclusive
            )

            val models = localEntities.map { it ->
                EventConverters.modelFromLocalEntity(it)
            }
            return models
        }

        /* at this point, get all events */
        val localEntities = eventsLocalDataSource.getAll()
        val models = localEntities.map { it ->
            EventConverters.modelFromLocalEntity(it)
        }
        return models
    }

    /* TODO this is not needed - we dont want to fetch event, because it will overwrite existing event that might be bookmarked */
    suspend fun loadEvent(id: Int): Unit {
        /* TODO this could potentially return nothing */
        val remoteEventEntity = eventsRemoteDataSource.getEvent(id)
            ?: return

        val localEventEntity = EventConverters.localEntityFromRemoteEntity(remoteEventEntity)

        eventsLocalDataSource.add(
            localEventEntity
        )
    }

    suspend fun getEvent(
        id: Int,
    ): EventModel? {
        val localEventEntity = eventsLocalDataSource.getOne(id)
            ?: return null

        val model = EventConverters.modelFromLocalEntity(localEventEntity)

        return model
    }

    suspend fun updateEventIsBookmarked(
        eventId: Int,
        isBookmarked: Boolean,
    ) {
        eventsLocalDataSource.updateEventIsBookmarked(
            UpdateEventLocalIsBookmarkedValue(
                eventId = eventId,
                isBookmarked = isBookmarked,
            )
        )
    }
}



