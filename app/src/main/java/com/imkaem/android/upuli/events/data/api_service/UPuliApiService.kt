package com.imkaem.android.upuli.events.data.api_service

import com.imkaem.android.upuli.events.data.remote.EventResponseDataRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventResponseRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventsResponseRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UPuliApiService {
    @GET("events")
    suspend fun getEvents(): EventsResponseRemoteEntity

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: Int): EventResponseRemoteEntity

    @GET("events")
    suspend fun getEventsByIds(
        /* TODO is there a way to pass list of ids, and then to have retrofit handle parsing to appropriate string */
        @Query("ids") ids: String
    ): EventsResponseRemoteEntity
}