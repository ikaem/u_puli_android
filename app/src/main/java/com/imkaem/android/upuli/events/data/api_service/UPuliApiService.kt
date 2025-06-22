package com.imkaem.android.upuli.events.data.api_service

import com.imkaem.android.upuli.events.data.remote.EventsResponseRemoteEntity
import retrofit2.http.GET

interface UPuliApiService {
    @GET("events")
    suspend fun getEvents(): EventsResponseRemoteEntity
}