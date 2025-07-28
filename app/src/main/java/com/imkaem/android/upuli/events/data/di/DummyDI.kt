package com.imkaem.android.upuli.events.data.di

import com.imkaem.android.upuli.UPuliApplication
import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.data.api_service.EventsRetrofitInstance
import com.imkaem.android.upuli.events.data.database.EventsRoomInstance
import com.imkaem.android.upuli.events.data.local.EventsLocalDataSource
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.use_cases.GetBookmarkedEventsFromDateFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetBookmarkedEventsFromDateUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetEventFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetEventUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetHomeScreenEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTodayEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTodayEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTomorrowEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTomorrowEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetUpcomingEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetUpcomingEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.LoadEventUseCase
import com.imkaem.android.upuli.events.domain.use_cases.LoadEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.UpdateEventIsBookmarkedUseCase


private val EVENTS_API_SERVICE = EventsRetrofitInstance.api

private val EVENTS_DAO = EventsRoomInstance.dao(
    context = UPuliApplication.getApplicationContext()
)

private val EVENTS_REMOTE_DATA_SOURCE = EventsRemoteDataSource(
    apiService = EVENTS_API_SERVICE
)
private val EVENTS_LOCAL_DATA_SOURCE = EventsLocalDataSource(
    dao = EVENTS_DAO
)
private val EVENTS_REPOSITORY = EventsRepository(
    eventsRemoteDataSource = EVENTS_REMOTE_DATA_SOURCE,
    eventsLocalDataSource = EVENTS_LOCAL_DATA_SOURCE
)

object DummyDI {
    val loadEventsUseCase = LoadEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val loadEventUseCase = LoadEventUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val updateEventIsBookmarkedUseCase = UpdateEventIsBookmarkedUseCase(
        eventsRepository = EVENTS_REPOSITORY,
    )

    val getHomeScreenEventsFlowUseCase = GetHomeScreenEventsFlowUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getTodayEventsFlowUseCase = GetTodayEventsFlowUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getTomorrowEventsFlowUseCase = GetTomorrowEventsFlowUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )

    val getEventFlowUseCase = GetEventFlowUseCase(
        eventsRepository = EVENTS_REPOSITORY,
    )

    val getBookmarkedEventsFromDateFlowUseCase = GetBookmarkedEventsFromDateFlowUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getEventUseCase = GetEventUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )

    /* NOT USED */

    val getBookmarkedEventsFromDateUseCase = GetBookmarkedEventsFromDateUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getTomorrowEventsUseCase = GetTomorrowEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getUpcomingEventsUseCase = GetUpcomingEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
    val getTodayEventsUseCase = GetTodayEventsUseCase(
        eventsRepository = EVENTS_REPOSITORY
    )
}
