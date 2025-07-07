package com.imkaem.android.upuli.events.presentation.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch

private const val TAG = "EventViewModel"

class EventScreenViewModel(

    /* TODO later, this will be provided by hilt probably */
    private val stateHandle: SavedStateHandle

) : ViewModel() {
    /* TODO temp, will later be provided by hilt */
    val getEventUseCase = DummyDI.getEventUseCase
    val loadEventsUseCase = DummyDI.loadEventUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase

    private val _state = mutableStateOf<EventScreenState>(generateInitialState())
    val state: State<EventScreenState>
        get() = _state

    init {
        getEvent()
    }

    fun onToggleEventIsBookmarked(
        id: Int,
    ) {

//        val event = state.value.allUpcomingEvents.firstOrNull { it ->
//            it.id == id
//        }

//        if (event == null) {
//            /* TODO maybe some error state, so a toast can be shown to indicate that the event was not found */
//            return
//        }

        val event = state.value.event
        if (event == null) {
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
            handleGetEvent(id = event.id)
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel cleared")

    }

    private fun getEvent() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */


        val id = stateHandle.get<Int>("event_id") ?: 0

        viewModelScope.launch {

            handleGetEvent(id)

//            loadEventsUseCase(id)
//            val event = getEventUseCase(id)
//
//            val updatedState = _state.value.copy(
//                event = event,
//                isLoading = false,
//                error = null
//            )
//
//            _state.value = updatedState
        }
    }

    private suspend fun handleGetEvent(id: Int) {

        val event = getEventUseCase(id)

        val updatedState = _state.value.copy(
            event = event,
            isLoading = false,
            error = null
        )

        _state.value = updatedState

    }

    private fun generateInitialState(): EventScreenState {
        return EventScreenState(
            event = null,
            isLoading = true,
            error = null
        )
    }
}