package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    /* TODO later, this will be provided by hilt probably */
    val getTodayEventsUseCase = DummyDI.getTodayEventsUseCase
    val getTomorrowEventsUseCase = DummyDI.getTomorrowEventsUseCase
    val getUpcomingEventsUseCase = DummyDI.getUpcomingEventsUseCase

    private val _state = mutableStateOf<EventsScreenState>(generateInitialState())
    val state: State<EventsScreenState>
        get() = _state


    init {
        loadEvents()
    }

    private fun loadEvents() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
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
}

private fun generateInitialState(): EventsScreenState {

    val state = (
        EventsScreenState(
//            todayEventsState = EventsScreenDayDayState(
//                featuredEvent = null,
//                dayEventsCount = 0,
//            ),
            todayEventsState = null,
//            tomorrowEventsState = EventsScreenDayDayState(
//                featuredEvent = null,
//                dayEventsCount = 0,
//            ),
            tomorrowEventsState = null,
            allUpcomingEvents = emptyList(),
            isLoading = true,
            error = null
        )
    )

    return state
}