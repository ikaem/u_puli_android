package com.imkaem.android.upuli.search.data

import com.imkaem.android.upuli.events.data.local.EventsLocalDataSource
import com.imkaem.android.upuli.events.utils.EventConverters
import com.imkaem.android.upuli.events.utils.values.UpdateEventLocalIsBookmarkedValue
import com.imkaem.android.upuli.search.data.remote.SearchRemoteDataSource
import com.imkaem.android.upuli.search.domain.models.SearchModel
import com.imkaem.android.upuli.search.utils.values.SearchResultIdsValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class SearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val eventsLocalDataSource: EventsLocalDataSource,

    /* TODO this will also accept events local data source, and possibly any other remote and local data source whose entities are fetched and stored */
) {

    /* TODO this can possibly be called loadSearchResults -> it loads those into db */
    suspend fun loadSearchResult(query: String): SearchResultIdsValue {

        /* TODO we already do this in load events - we should unify this somehow so as not to repeat ourselves */
        val bookmarkedEvents = eventsLocalDataSource.getAllBookmarked()

        val remoteEntity = searchRemoteDataSource.search(query)

        val localEventEntities = remoteEntity.events.map { it ->
            EventConverters.localEntityFromRemoteEntity(it)
        }
        eventsLocalDataSource.addAll(
            /* TODO maybe this can be adjusted somehow in options to override all fields except isBookmarked if event exists */
            localEventEntities
        )

        /* to make sure that correct events are updated as bookmarked */
        val values = bookmarkedEvents.map { it ->
            UpdateEventLocalIsBookmarkedValue(
                eventId = it.id,
                isBookmarked = it.isBookmarked,
            )
        }
        eventsLocalDataSource.updateEventsIsBookmarked(
            updateValues = values,
        )


        return SearchResultIdsValue(
            eventIds = remoteEntity.events.map { it.id }
        )
    }

    fun getSearchResultFlow(
        idsValue: SearchResultIdsValue,
    ): Flow<SearchModel> {
        val eventsFlow = eventsLocalDataSource.getAllByIdsFlow(ids = idsValue.eventIds)
        /* if we have another flow, we can get it here*/


        /* then we need to combine multiple flows into one in case we have multiple flows */
        val combinedFlow = eventsFlow.map { localEntities ->
            val searchModel = SearchModel(
                events = localEntities.map { localEntity -> EventConverters.modelFromLocalEntity(localEntity) },
            )

            searchModel
        }

        return combinedFlow
    }
}