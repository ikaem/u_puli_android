package com.imkaem.android.upuli.events.data.remote

import com.imkaem.android.upuli.events.data.api_service.UPuliApiService
import com.imkaem.android.upuli.events.domain.models.EventModel

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

    /* TODO later, this should probably be delegated to local data source, where we would pass */
}