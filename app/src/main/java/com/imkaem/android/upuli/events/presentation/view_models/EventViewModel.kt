package com.imkaem.android.upuli.events.presentation.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.DummyData

private const val TAG = "EventViewModel"

class EventViewModel(

    private val stateHandle: SavedStateHandle

) : ViewModel() {

    private val _state = mutableStateOf<EventModel?>(null)
    val state: State<EventModel?>
        get() = _state

    init {

        val id = stateHandle.get<Int>("event_id") ?: 0
        val event = DummyData.dummyEvents.find { it ->
            it.id == id
        }
        _state.value = event
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel cleared")

    }
}