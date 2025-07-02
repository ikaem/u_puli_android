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

    private val _state = mutableStateOf<EventScreenState>(generateInitialState())
    val state: State<EventScreenState>
        get() = _state

    init {
        getEvent()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel cleared")

    }

    private fun getEvent() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */

        viewModelScope.launch {
            val id = stateHandle.get<Int>("event_id") ?: 0

            loadEventsUseCase(id)
            val event = getEventUseCase(id)

            val updatedState = _state.value.copy(
                event = event,
                isLoading = false,
                error = null
            )

            _state.value = updatedState
        }
    }

    private fun generateInitialState(): EventScreenState {
        return EventScreenState(
            event = null,
            isLoading = true,
            error = null
        )
    }
}