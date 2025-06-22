package com.imkaem.android.upuli.events.data

import com.imkaem.android.upuli.events.data.local.EventsLocalDataSource
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.EventConverters

class EventsRepository(
    private val eventsRemoteDataSource: EventsRemoteDataSource,
    private val eventsLocalDataSource: EventsLocalDataSource,
) {
    /* TODO eventually, this could get filter*/
    suspend fun loadEvents() {
        val remoteEventEntities = eventsRemoteDataSource.getEvents()

        val localEventEntities = remoteEventEntities.map { it ->
            EventConverters.localEntityFromRemoteEntity(it)
        }

        eventsLocalDataSource.addAll(
            localEventEntities
        )
    }

    /* TODO maybe too maby functions here, will see */
    suspend fun getEvents(
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

    /* --------------- dummy data ---------------*/
    /* TODO in future, this will actually get data from database */
//    suspend fun getEvents(
//        filter: GetEventsFilter,
//    ): List<EventModel> {
//        /* TODO remember that date and time is off becasue of timezone i guess... not sure if i shoul be handling this on server (maybe in dart, i need to specify this timezone */
//        val from = filter.fromDateMilliseconds
//        val to = filter.toDateMilliseconds
//
//
//        /* TODO later, use withContext, and pass it ui dispatcher */
//        val remoteEventEntities = eventsRemoteDataSource.getEvents()
//
//        val shouldFilterFromAndTo = from != null && to != null
//        if (shouldFilterFromAndTo) {
//            val filtered = filterToAndFrom(
//                remoteEventEntities,
//                from,
//                to,
//            )
//
//            val models = filtered.map { it ->
//                val model = EventConverters.modelFromRemoteEntity(it)
//                return@map model
//            }
//
//            return models
//        }
//
//        val shouldFilterOnlyFrom = from != null
//        if (shouldFilterOnlyFrom) {
//            val filtered = filterFrom(
//                remoteEventEntities,
//                from,
//            )
//
//            val models = filtered.map { it ->
//                val model = EventConverters.modelFromRemoteEntity(it)
//                return@map model
//            }
//
//            return models
//        }
//
//        val shouldFilterOnlyTo = to != null
//        if (shouldFilterOnlyTo) {
//            val filtered = filterTo(
//                remoteEventEntities,
//                to,
//            )
//
//            val models = filtered.map { it ->
//                val model = EventConverters.modelFromRemoteEntity(it)
//                return@map model
//            }
//
//            return models
//        }
//
//
//        val models = remoteEventEntities.map { it ->
//            val model = EventConverters.modelFromRemoteEntity(it)
//            return@map model
//        }
//        return models
//    }


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


//private fun filterToAndFrom(
//    events: List<EventRemoteEntity>,
//    from: Long,
//    to: Long,
//): List<EventRemoteEntity> {
//    val filtered = events.filter { it ->
//        val eventDate = it.dateInMilliseconds;
//
//        if (eventDate >= from && eventDate < to) {
//            return@filter true
//        }
//
//        return@filter false
//    }
//    return filtered;
//}

/*TODO this is only for testing */
//private fun filterFrom(
//    events: List<EventRemoteEntity>,
//    from: Long,
//): List<EventRemoteEntity> {
//
//    val filtered = events.filter { it ->
//        val eventDate = it.dateInMilliseconds;
//
//        if (eventDate >= from) {
//            return@filter true
//        }
//
//        return@filter false
//
//    }
//
//    return filtered
//}
//
//private fun filterTo(
//    events: List<EventRemoteEntity>,
//    to: Long,
//): List<EventRemoteEntity> {
//
//    val filtered = events.filter { it ->
//        val eventDate = it.dateInMilliseconds;
//
//        if (eventDate <= to) {
//            return@filter true
//        }
//
//        return@filter false
//
//    }
//
//    return filtered
//}