package com.imkaem.android.upuli.events.data

import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.DummyData
import com.imkaem.android.upuli.events.utils.EventConverters
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

class EventsRepository(
    private val eventsRemoteDataSource: EventsRemoteDataSource
) {

    /* TODO in future, this will actually get data from database */
    suspend fun getEvents(): List<EventModel> {
        /* TODO make some kind of helper for this */
        /* also, maybe creating a formatter is expensive, not sure */
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())


        /* TODO later, use withContext, and pass it ui dispatcher */
        val remoteEventEntities = eventsRemoteDataSource.getEvents()

        val eventModels = remoteEventEntities.map { it ->
            val dateInSeconds = it.dateInMilliseconds / 1000L
            val localDateTime = LocalDateTime.ofEpochSecond(
                dateInSeconds,
                0,
                /* TODO not sure if this will be ok - maybe we an use some lib or something */
                ZoneOffset.UTC,
            )
            val formattedDate = localDateTime.format(dateFormatter)
            val formattedTime = localDateTime.format(timeFormatter)
            val eventModel = EventModel(
                id = it.id,
                title = it.title,
                location = it.location,
                date = formattedDate,
                time = formattedTime,
            )

            return@map eventModel
        }

        return eventModels
    }

    /* TODO below is temp only */

    suspend fun getDummyModelEvent(
        id: Int
    ): EventModel? {
        val remoteEvent = DummyData.dummyRemoteEvents.firstOrNull {
            it.id == id
        }

        /* THIS IS COOL SYNTAX - pass event if it exist - if not, return from the function */
        val model = EventConverters.modelFromRemoteEntity(remoteEvent ?: return null)

        return model
    }


    suspend fun getDummyModelEvents(
        filter: GetEventsFilter
    ): List<EventModel> {

        val from = filter.fromDateMilliseconds
        val to = filter.toDateMilliseconds

        val allRemoteEntityEvents = DummyData.dummyRemoteEvents

        val shouldFilterFromAndTo = from != null && to != null
        if (shouldFilterFromAndTo) {
            val filtered = filterToAndFrom(
                allRemoteEntityEvents,
                from,
                to,
            )

            val models = filtered.map { it ->
                val model = EventConverters.modelFromRemoteEntity(it)
                return@map model
            }

            return models
        }

        val shouldFilterOnlyFrom = from != null
        if (shouldFilterOnlyFrom) {
            val filtered = filterFrom(
                allRemoteEntityEvents,
                from,
            )

            val models = filtered.map { it ->
                val model = EventConverters.modelFromRemoteEntity(it)
                return@map model
            }

            return models
        }

        val shouldFilterOnlyTo = to != null
        if (shouldFilterOnlyTo) {
            val filtered = filterTo(
                allRemoteEntityEvents,
                to,
            )

            val models = filtered.map { it ->
                val model = EventConverters.modelFromRemoteEntity(it)
                return@map model
            }

            return models
        }


        val models = allRemoteEntityEvents.map { it ->
            val model = EventConverters.modelFromRemoteEntity(it)
            return@map model
        }
        return models
    }

    /* TODO this is not to be used*/
    suspend fun getDummyRemoteEvents(
        filter: GetEventsFilter,
    ): List<EventRemoteEntity> {

        val from = filter.fromDateMilliseconds
        val to = filter.toDateMilliseconds

        val allEvents = eventsRemoteDataSource.getEvents()

        val shouldFilterFromAndTo = from != null && to != null
        if (shouldFilterFromAndTo) {
            return filterToAndFrom(
                allEvents,
                from,
                to,
            )
        }
        /* now we know something or both are null */
        val shouldFilterOnlyFrom = from != null

        if (shouldFilterOnlyFrom) {
            return filterFrom(
                allEvents,
                from,
            )
        }

        val shouldFilterOnlyTo = to != null
        if (shouldFilterOnlyTo) {
            return filterTo(
                allEvents,
                to,
            )
        }

        /* now we know both filters are null, so we can return all events */
        return allEvents
    }
}
/* ------------ filter models --------------*/


/* ------------ filter entities ---------------- */
private fun filterToAndFrom(
    events: List<EventRemoteEntity>,
    from: Long,
    to: Long,
): List<EventRemoteEntity> {
    val filtered = events.filter { it ->
        val eventDate = it.dateInMilliseconds;

        if (eventDate >= from && eventDate < to) {
            return@filter true
        }

        return@filter false
    }
    return filtered;
}

/*TODO this is only for testing */
private fun filterFrom(
    events: List<EventRemoteEntity>,
    from: Long,
): List<EventRemoteEntity> {

    val filtered = events.filter { it ->
        val eventDate = it.dateInMilliseconds;

        if (eventDate >= from) {
            return@filter true
        }

        return@filter false

    }

    return filtered
}

private fun filterTo(
    events: List<EventRemoteEntity>,
    to: Long,
): List<EventRemoteEntity> {

    val filtered = events.filter { it ->
        val eventDate = it.dateInMilliseconds;

        if (eventDate <= to) {
            return@filter true
        }

        return@filter false

    }

    return filtered
}