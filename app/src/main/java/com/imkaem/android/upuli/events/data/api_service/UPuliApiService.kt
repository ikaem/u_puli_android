package com.imkaem.android.upuli.events.data.api_service

import com.imkaem.android.upuli.events.data.remote.EventResponseDataRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventResponseRemoteEntity
import com.imkaem.android.upuli.events.data.remote.EventsResponseRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface UPuliApiService {
    @GET("events")
    suspend fun getEvents(): EventsResponseRemoteEntity

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: Int): EventResponseRemoteEntity
}