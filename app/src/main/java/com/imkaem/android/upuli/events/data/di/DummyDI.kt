package com.imkaem.android.upuli.events.data.di

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.data.api_service.RetrofitInstance
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.use_cases.GetEventUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTodayEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetUpcomingEventsUseCase


private val API_SERVICE = RetrofitInstance.api

private val EVENTS_REMOTE_DATA_SOURCE = EventsRemoteDataSource(
    apiService = API_SERVICE
)
private val EVENTS_REPOSITORY = EventsRepository(
    eventsRemoteDataSource = EVENTS_REMOTE_DATA_SOURCE
)

object DummyDI {
    val getTodayEventsUseCase = GetTodayEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getTomorrowEventsUseCase = GetTodayEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getUpcomingEventsUseCase = GetUpcomingEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getEventUseCase = GetEventUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
}