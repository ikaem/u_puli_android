package com.imkaem.android.upuli.events.data

import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.DummyData
import com.imkaem.android.upuli.events.utils.EventConverters

class EventsRepository(
    private val eventsRemoteDataSource: EventsRemoteDataSource
) {

    suspend fun getEvent(
        id: Int,
    ): EventModel? {

        val remoteEventEntity = eventsRemoteDataSource.getEvent(id)
            ?: return null

        val model = EventConverters.modelFromRemoteEntity(remoteEventEntity)
        return model
    }

    /* TODO in future, this will actually get data from database */
    suspend fun getEvents(
        filter: GetEventsFilter,
    ): List<EventModel> {
        /* TODO remember that date and time is off becasue of timezone i guess... not sure if i shoul be handling this on server (maybe in dart, i need to specify this timezone */
        val from = filter.fromDateMilliseconds
        val to = filter.toDateMilliseconds


        /* TODO later, use withContext, and pass it ui dispatcher */
        val remoteEventEntities = eventsRemoteDataSource.getEvents()

        val shouldFilterFromAndTo = from != null && to != null
        if (shouldFilterFromAndTo) {
            val filtered = filterToAndFrom(
                remoteEventEntities,
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
                remoteEventEntities,
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
                remoteEventEntities,
                to,
            )

            val models = filtered.map { it ->
                val model = EventConverters.modelFromRemoteEntity(it)
                return@map model
            }

            return models
        }


        val models = remoteEventEntities.map { it ->
            val model = EventConverters.modelFromRemoteEntity(it)
            return@map model
        }
        return models
    }

    /* TODO below is temp only */

//    suspend fun getDummyModelEvent(
//        id: Int
//    ): EventModel? {
//        val remoteEvent = DummyData.dummyRemoteEvents.firstOrNull {
//            it.id == id
//        }
//
//        /* THIS IS COOL SYNTAX - pass event if it exist - if not, return from the function */
//        val model = EventConverters.modelFromRemoteEntity(remoteEvent ?: return null)
//
//        return model
//    }


}


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