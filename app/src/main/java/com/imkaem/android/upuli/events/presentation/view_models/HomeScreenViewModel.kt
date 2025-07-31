package com.imkaem.android.upuli.events.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeScreenViewModel : ViewModel() {
    val loadEventsUseCase = DummyDI.loadEventsUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase
    val getHomeScreenEventsFlowUseCase = DummyDI.getHomeScreenEventsFlowUseCase

    private val _state = MutableStateFlow<HomeScreenState>(generateInitialState())
    val state: StateFlow<HomeScreenState>
        get() = _state


    init {
        handleGenerateState()
    }


    private fun handleGenerateState() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            /* first load remote events into database */
            loadEvents()

            /* then get all events from db and set into state */
            getStateFromEventsFlow()
        }
    }


    private suspend fun loadEvents() {
        loadEventsUseCase()
    }

    private suspend fun getStateFromEventsFlow() {
        getHomeScreenEventsFlowUseCase().collect { result ->
            val todayFeaturedEvent = result.todayEvents.firstOrNull()
            val todayEventsCount = result.todayEvents.size

            val tomorrowFeaturedEvent = result.tomorrowEvents.firstOrNull()
            val tomorrowEventsCount = result.tomorrowEvents.size

            /* TODO lets keep here for now */
            val existingAllEvents = _state.value.allUpcomingEvents
            val newAllEvents = result.allUpcomingEvents


            val newState = _state.value.copy(
                todayEventsState = todayFeaturedEvent?.let {
                    HomeScreenDayState(
                        featuredEvent = it,
                        dayEventsCount = todayEventsCount
                    )
                },
                tomorrowEventsState = tomorrowFeaturedEvent?.let {
                    HomeScreenDayState(
                        featuredEvent = it,
                        dayEventsCount = tomorrowEventsCount
                    )
                },
                allUpcomingEvents = existingAllEvents + newAllEvents,
                isLoading = false,
                error = null,
            )


//            val newState = HomeScreenState(
//                todayEventsState = todayFeaturedEvent?.let {
//                    HomeScreenDayState(
//                        featuredEvent = it,
//                        dayEventsCount = todayEventsCount
//                    )
//                },
//                tomorrowEventsState = tomorrowFeaturedEvent?.let {
//                    HomeScreenDayState(
//                        featuredEvent = it,
//                        dayEventsCount = tomorrowEventsCount
//                    )
//                },
//                /* this approach would not allow pagination, as I only show new events
//                * so i have to create logic that will merge existing events with new ones, but take into consideration the bookmarked state of each event
//                *  */
//                allUpcomingEvents = result.allUpcomingEvents,
//                isLoading = false,
//                error = null,
//                selectedTabIndex = 0,
//            )

            _state.update {
                newState
            }
        }

    }

    fun onToggleEventIsBookmarked(
        id: Int,
    ) {

        val event = _state.value.allUpcomingEvents.firstOrNull { it ->
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
        }
    }

    fun onSelectTab(index: Int) {
        _state.update { it.copy(selectedTabIndex = index) }
    }
}

private fun generateInitialState(): HomeScreenState {
    val state = (
            HomeScreenState(
                todayEventsState = null,
                tomorrowEventsState = null,
                allUpcomingEvents = emptyList(),
                isLoading = true,
                error = null,
                selectedTabIndex = 0,
            )
            )

    return state
}