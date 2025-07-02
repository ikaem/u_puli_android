package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch

class EventsScreenViewModel : ViewModel() {

    /* TODO later, this will be provided by hilt probably */
    val loadEventsUseCase = DummyDI.loadEventsUseCase
    val getTodayEventsUseCase = DummyDI.getTodayEventsUseCase
    val getTomorrowEventsUseCase = DummyDI.getTomorrowEventsUseCase
    val getUpcomingEventsUseCase = DummyDI.getUpcomingEventsUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase


    private val _state = mutableStateOf<EventsScreenState>(generateInitialState())
    val state: State<EventsScreenState>
        get() = _state


    init {
        getEvents()
    }

    fun onToggleEventIsBookmarked(
        id: Int,
    ) {

        val event = state.value.allUpcomingEvents.firstOrNull { it ->
            it.id == id
        }

        if (event == null) {
            /* TODO maybe some error state, so a toast can be shown to indicate that the event was not found */
            return
        }

        /* TODO  this needs some error handler, and IO dispatcher */
        viewModelScope.launch {
            updateEventIsBookmarkedUseCase(
                id = event.id,
                oldIsBookmarked = event.isBookmarked
            )

            /* TODO maybe some flow here would be better - then we would only fetch all upcoming, and not today or tomorrow events (which dont have bookmark option on them */
            /* i dont know, this needs to be worked out */
            /* also, maybe it is overkill to load ALL events from db just because ONE SINGLE event got its isBookmarked field set */

            /* reload fresh events from db */
            handleGetEvents()


        }

    }


    private fun getEvents() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        /* TODO missing error handling */
        viewModelScope.launch {

            /* first load remote events into database */
            /* TODO maybe naming for load events is bad - because it would be greate to call other function here loadEvents */
            loadEventsUseCase()

            /* then get all events from db and set into state */
            handleGetEvents()


//            /* get today events */
//            val todayEvents = getTodayEventsUseCase()
//            val todayFeaturedEvent = todayEvents.firstOrNull()
//            val todayEventsCount = todayEvents.size
//
//            /* get tomorrow events */
//            val tomorrowEvents = getTomorrowEventsUseCase()
//            val tomorrowFeaturedEvent = tomorrowEvents.firstOrNull()
//            val tomorrowEventsCount = tomorrowEvents.size
//
//            /* get upcoming events */
//            val upcomingEvents = getUpcomingEventsUseCase()
//
//
//            val updatedState = _state.value.copy(
//                todayEventsState = todayFeaturedEvent?.let {
//                    EventsScreenDayState(
//                        featuredEvent = it,
//                        dayEventsCount = todayEventsCount
//                    )
//                },
//                tomorrowEventsState = tomorrowFeaturedEvent?.let {
//                    EventsScreenDayState(
//                        featuredEvent = it,
//                        dayEventsCount = tomorrowEventsCount
//                    )
//                },
//                allUpcomingEvents = upcomingEvents,
//                isLoading = false,
//                error = null
//            )
//
//            _state.value = updatedState
        }
    }

    suspend fun handleGetEvents() {
        /* get today events */
        val todayEvents = getTodayEventsUseCase()
        val todayFeaturedEvent = todayEvents.firstOrNull()
        val todayEventsCount = todayEvents.size

        /* get tomorrow events */
        val tomorrowEvents = getTomorrowEventsUseCase()
        val tomorrowFeaturedEvent = tomorrowEvents.firstOrNull()
        val tomorrowEventsCount = tomorrowEvents.size

        /* get upcoming events */
        val upcomingEvents = getUpcomingEventsUseCase()


        val updatedState = _state.value.copy(
            todayEventsState = todayFeaturedEvent?.let {
                EventsScreenDayState(
                    featuredEvent = it,
                    dayEventsCount = todayEventsCount
                )
            },
            tomorrowEventsState = tomorrowFeaturedEvent?.let {
                EventsScreenDayState(
                    featuredEvent = it,
                    dayEventsCount = tomorrowEventsCount
                )
            },
            allUpcomingEvents = upcomingEvents,
            isLoading = false,
            error = null
        )

        _state.value = updatedState
    }
}

private fun generateInitialState(): EventsScreenState {
    val state = (
            EventsScreenState(
                todayEventsState = null,
                tomorrowEventsState = null,
                allUpcomingEvents = emptyList(),
                isLoading = true,
                error = null,
            )
            )

    return state
}