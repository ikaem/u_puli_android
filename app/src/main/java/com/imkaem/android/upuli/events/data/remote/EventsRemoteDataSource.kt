package com.imkaem.android.upuli.events.data.remote

import com.imkaem.android.upuli.events.data.api_service.UPuliApiService

/* TODO also, make an interface for this */
/* TODO maybe would be good to place this in data_sources directory */
class EventsRemoteDataSource(
    private val apiService: UPuliApiService,
    /* TODO will need to pass in ui dispatcher here, to be used when fetching data */
) {

    suspend fun getEvents(): List<EventRemoteEntity> {
        val response = apiService.getEvents()
        if (!response.ok) {
           throw Exception("Error fetching events: ${response.message}")
        }

        return response.data.events
    }

    suspend fun getEvent(id: Int): EventRemoteEntity? {
        val response = apiService.getEvent(id)
        if(!response.ok) {
            throw Exception("Error fetching event with id $id: ${response.message}")
        }

        return response.data?.event
    }

    /* TODO this is not used */
    suspend fun getEventsByIds(
        ids: List<Int>
    ): List<EventRemoteEntity> {
        val idsString = ids.joinToString(",")
        val response = apiService.getEventsByIds(
            ids = idsString
        )
        if(!response.ok) {
            throw Exception("Error fetching events with ids $ids: ${response.message}")
        }

        return response.data.events
    }

    /* TODO later, this should probably be delegated to local data source, where we would pass */
}