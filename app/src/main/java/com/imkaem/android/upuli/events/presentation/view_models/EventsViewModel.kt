package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.events.utils.DummyData

class EventsViewModel: ViewModel() {

    private val _state = mutableStateOf<List<EventModel>>(emptyList())
    val state: State<List<EventModel>>
        get() = _state;


    init {
        val events = DummyData.dummyEvents
        _state.value = events
    }
}