package com.imkaem.android.upuli.core.utils.di

import com.imkaem.android.upuli.UPuliApplication
import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.core.data.api_service.UPuliRetrofitInstance
import com.imkaem.android.upuli.core.data.database.UPuliDatabaseInstance
import com.imkaem.android.upuli.events.data.local.EventsLocalDataSource
import com.imkaem.android.upuli.events.data.remote.EventsRemoteDataSource
import com.imkaem.android.upuli.events.domain.use_cases.GetBookmarkedEventsFromDateFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetEventFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetEventUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetHomeScreenEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTodayEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.GetTomorrowEventsFlowUseCase
import com.imkaem.android.upuli.events.domain.use_cases.LoadEventUseCase
import com.imkaem.android.upuli.events.domain.use_cases.LoadEventsUseCase
import com.imkaem.android.upuli.events.domain.use_cases.UpdateEventIsBookmarkedUseCase
import com.imkaem.android.upuli.search.data.remote.SearchRemoteDataSource
import com.imkaem.android.upuli.search.data.remote.SearchRepository
import com.imkaem.android.upuli.search.domain.use_cases.SearchUseCase


private val API_SERVICE = UPuliRetrofitInstance.api

private val EVENTS_DAO = UPuliDatabaseInstance.dao(
    context = UPuliApplication.getApplicationContext()
)

/* events */
private val EVENTS_REMOTE_DATA_SOURCE = EventsRemoteDataSource(
    apiService = API_SERVICE
)
private val EVENTS_LOCAL_DATA_SOURCE = EventsLocalDataSource(
    dao = EVENTS_DAO
)
private val EVENTS_REPOSITORY = EventsRepository(
    eventsRemoteDataSource = EVENTS_REMOTE_DATA_SOURCE,
    eventsLocalDataSource = EVENTS_LOCAL_DATA_SOURCE
)

/* search */
private val SEARCH_REMOTE_DATA_SOURCE = SearchRemoteDataSource(
    apiService = API_SERVICE
)
private val SEARCH_REPOSITORY = SearchRepository(
    searchRemoteDataSource = SEARCH_REMOTE_DATA_SOURCE
)



object DummyDI {
    /* events */
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

    /* search */
    val searchUseCase = SearchUseCase(
        searchRepository = SEARCH_REPOSITORY
    )

}
