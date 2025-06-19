package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imkaem.android.upuli.events.utils.DummyData
import java.time.LocalDate
import java.time.LocalDateTime

class EventsViewModel : ViewModel() {

    private val _state = mutableStateOf<EventsScreenState>(generateInitialState())
    val state: State<EventsScreenState>
        get() = _state

    /*    private val _state = mutableStateOf<List<EventModel>>(emptyList())
        val state: State<List<EventModel>>
            get() = _state;*/


    init {
        val events = DummyData.dummyEvents
        val todayEvents = events.filter { it ->

            val todayLocalDate = LocalDate.now()
            val eventLocalDate = it.date.toLocalDate()

            return@filter eventLocalDate.isEqual(todayLocalDate);
        }

        val todayFeaturedEvent = todayEvents.firstOrNull()
        val todayEventsCount = todayEvents.size

        val tomorrowEvents = events.filter { it ->
            val tomorrowLocalDate = LocalDate.now().plusDays(1)
            val eventLocalDate = it.date.toLocalDate()

            return@filter eventLocalDate.isEqual(tomorrowLocalDate);
        }

        val tomorrowFeaturedEvent = tomorrowEvents.firstOrNull()
        val tomorrowEventsCount = tomorrowEvents.size

        val allUpcomingEvents = events.filter { it.date.isAfter(LocalDateTime.now()) }

        val temp = EventsScreenState(
//            todayEventsState = EventsScreenDayDayState(
//                featuredEvent = todayFeaturedEvent,
//                dayEventsCount = todayEventsCount,
//            ),
            todayEventsState = todayFeaturedEvent?.let {
                EventsScreenDayState(
                    featuredEvent = it,
                    dayEventsCount = todayEventsCount,
                )
            },
//            tomorrowEventsState = EventsScreenDayDayState(
//                featuredEvent = tomorrowFeaturedEvent,
//                dayEventsCount = tomorrowEventsCount,
//            ),
            tomorrowEventsState = tomorrowFeaturedEvent?.let {
                EventsScreenDayState(
                    featuredEvent = it,
                    dayEventsCount = tomorrowEventsCount,
                )
            },
//            allUpcomingEvents = events.filter { it.date.isAfter(LocalDateTime.now()) },
            allUpcomingEvents = allUpcomingEvents,
            isLoading = false,
            error = null
        )

        val something = _state.value.copy(
            todayEventsState = temp.todayEventsState,
            tomorrowEventsState = temp.tomorrowEventsState,
            allUpcomingEvents = temp.allUpcomingEvents,
            isLoading = temp.isLoading,
            error = temp.error
        )

        _state.value = something
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
            isLoading = false,
            error = null
        )
    )

    return state
}