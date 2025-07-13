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

/* TODO this view model is same as Today one - maybe they can be merged somehow - maybe the screenitself can accept argument*/
class TomorrowEventsScreenViewModel : ViewModel() {
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase
    val getTomorrowEventsFlowUseCase = DummyDI.getTomorrowEventsFlowUseCase

    private val _state = MutableStateFlow<TomorrowEventsScreenState>(
        TomorrowEventsScreenState(
            events = emptyList(),
            isLoading = true,
            error = null,
        )
    )

    val state: StateFlow<TomorrowEventsScreenState>
        get() = _state


    init {
        handleGenerateState()
    }


    private fun handleGenerateState() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            getStateFromEventsFlow()
        }
    }


    private suspend fun getStateFromEventsFlow() {
        getTomorrowEventsFlowUseCase().collect { events ->
            val newState = TomorrowEventsScreenState(
                events = events,
                isLoading = false,
                error = null,
            )

            _state.update { newState }
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
        }
    }
}