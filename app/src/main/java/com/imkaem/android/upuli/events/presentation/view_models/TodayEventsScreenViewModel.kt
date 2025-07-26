package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodayEventsScreenViewModel : ViewModel() {
    /* NOTE: note used anymore, but keep here for now */
//    val getTodayEventsUseCase = DummyDI.getTodayEventsUseCase
    /* TODO not sure if there shoul be a view model that updates bookmarked event, and then reused in each screen - or maybe in activity, and method is passed to each screen - I dont know... */
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase

    val getTodayEventsFlowUseCase = DummyDI.getTodayEventsFlowUseCase

    /* TODO lets leave here for now - for some future reference */
//    private val _state = mutableStateOf(
//        TodayEventsScreenState(
//            events = emptyList(),
//            isLoading = true,
//            error = null
//        )
//    )
//    val state: State<TodayEventsScreenState>
//        get() = _state

    private val _state = MutableStateFlow<TodayEventsScreenState>(
        TodayEventsScreenState(
            events = emptyList(),
            isLoading = true,
            error = null,
        )
    )
    val state: StateFlow<TodayEventsScreenState>
        get() = state;


    init {
//        getEvents()
        handleGenerateState()
    }

    private fun handleGenerateState() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            getStateFromEventsFlow()
        }

    }

    private suspend fun getStateFromEventsFlow() {
        getTodayEventsFlowUseCase().collect { events ->
            val newState = TodayEventsScreenState(
                events = events,
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

        val event = state.value.events.firstOrNull { it ->
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

            /* no need to do this - because the flow will take care of it */
//            handleGetEvents()
        }
    }


    /* TODO old no-flow implementation - keep here for now */
//    private fun getEvents() {
//        /* TODO some error handler, and explicit IO dispatcher should be passed in */
//        /* TODO missing error handling */
//        viewModelScope.launch {
//            handleGetEvents()
//        }
//    }
//
//
//    private suspend fun handleGetEvents() {
//        val todayEvents = getTodayEventsUseCase()
//
//        val updatedState = _state.value.copy(
//            events = todayEvents,
//            isLoading = false,
//            error = null,
//        )
//
//        _state.value = updatedState
//    }
}