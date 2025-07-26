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
    val loadEventUseCase = DummyDI.loadEventUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase

    private val _state = mutableStateOf<EventScreenState>(generateInitialState())
    val state: State<EventScreenState>
        get() = _state

    init {
//        getEvent()

        handleGenerateState()
    }

    private fun handleGenerateState() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            val id = getIdFromNavArgs()

            loadEvent(id)

            getStateFromEvent(id)
        }
    }

    private suspend fun getStateFromEvent(id: Int) {

        val event = getEventUseCase(id)

        val updatedState = _state.value.copy(
            event = event,
            isLoading = false,
            error = null
        )

        _state.value = updatedState
    }

    private fun getIdFromNavArgs(): Int {

            /* TODO this will need to be handled somewhere, to emit error */
        val id = stateHandle.get<Int>("event_id")
            ?: throw IllegalArgumentException("Event ID not found in navigation arguments")


        return id

    }

    private suspend fun loadEvent(id: Int) {
        loadEventUseCase(id)
    }

    fun onToggleEventIsBookmarked(
        /* TODO this is not even needed argument */
        id: Int,
    ) {

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
            getStateFromEvent(id = event.id)
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel cleared")

    }

//    private fun getEvent() {
//        val id = stateHandle.get<Int>("event_id") ?: 0
//
//        /* TODO some error handler, and explicit IO dispatcher should be passed in */
//        viewModelScope.launch {
//
//            handleGetEvent(id)
//        }
//    }

//    private suspend fun handleGetEvent(id: Int) {
//
//        val event = getEventUseCase(id)
//
//        val updatedState = _state.value.copy(
//            event = event,
//            isLoading = false,
//            error = null
//        )
//
//        _state.value = updatedState
//    }

    private fun generateInitialState(): EventScreenState {
        return EventScreenState(
            event = null,
            isLoading = true,
            error = null
        )
    }
}