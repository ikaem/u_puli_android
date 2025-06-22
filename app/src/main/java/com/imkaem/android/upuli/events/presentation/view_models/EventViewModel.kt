package com.imkaem.android.upuli.events.presentation.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.domain.use_cases.GetEventUseCase
import com.imkaem.android.upuli.events.utils.DummyData
import kotlinx.coroutines.launch

private const val TAG = "EventViewModel"

class EventViewModel(

    /* TODO later, this will be provided by hilt probably */

    private val stateHandle: SavedStateHandle

) : ViewModel() {
    val getEventUseCase = DummyDI.getEventUseCase

    private val _state = mutableStateOf<EventScreenState>(generateInitialState())
    val state: State<EventScreenState>
        get() = _state

    init {

//        val id = stateHandle.get<Int>("event_id") ?: 0
//        val event = DummyData.dummyEvents.find { it ->
//            it.id == id
//        }
//        _state.value = event

        loadEvent()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel cleared")

    }

    private fun loadEvent() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */

        viewModelScope.launch {
            val event = getEventUseCase(stateHandle.get<Int>("event_id") ?: 0)

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