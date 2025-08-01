package com.imkaem.android.upuli.events.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class HomeScreenViewModel : ViewModel() {
    val loadEventsUseCase = DummyDI.loadEventsUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase
    val getHomeScreenEventsFlowUseCase = DummyDI.getHomeScreenEventsFlowUseCase

    private val _state = MutableStateFlow<HomeScreenState>(generateInitialState())
    val state: StateFlow<HomeScreenState>
        get() = _state


    /* TODO i guess this is temp */
    private val today = LocalDate.now()
    private val tomorrow = today.plusDays(1)

    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
    val todayString: String
        get() = today.format(dateFormatter)
    val tomorrowString: String
        get() = tomorrow.format(dateFormatter)


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
        getHomeScreenEventsFlowUseCase(
            today = today,
            tomorrow = tomorrow,
        ).collect { result ->
            val todayFeaturedEvent = result.todayEvents.firstOrNull()
            val todayEventsCount = result.todayEvents.size
//            val todayAllEventsString = result.todayEvents.joinToString(
//                separator = "  |  ",
//            )
            val todayAllEventsString = result.todayEvents.joinToString(
                separator = "  |  ",
            ) { it.title }

            val tomorrowFeaturedEvent = result.tomorrowEvents.firstOrNull()
            val tomorrowEventsCount = result.tomorrowEvents.size
            /* TODO make this automatic somehow */
            val tomorrowAllEventsString = result.tomorrowEvents.joinToString(
                separator = "  |  ",
            ) { it.title }

            /* TODO lets keep here for now */
            val existingAllEvents = _state.value.allUpcomingEvents
            val newAllEvents = result.allUpcomingEvents


            val newState = _state.value.copy(
                todayEventsState = todayFeaturedEvent?.let {
                    HomeScreenDayState(
                        featuredEvent = it,
                        dayEventsCount = todayEventsCount,
                        allEventsString = todayAllEventsString,
                    )
                },
                tomorrowEventsState = tomorrowFeaturedEvent?.let {
                    HomeScreenDayState(
                        featuredEvent = it,
                        dayEventsCount = tomorrowEventsCount,
                        allEventsString = tomorrowAllEventsString,
                    )
                },
                allUpcomingEvents = newAllEvents,
                isLoading = false,
                error = null,
            )

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