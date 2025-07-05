package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch

/* TODO this view model is same as Today one - maybe they can be merged somehow - maybe the screenitself can accept argument*/
class TomorrowEventsScreenViewModel: ViewModel() {
    val getTomorrowEventsUseCase = DummyDI.getTomorrowEventsUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase

    private val _state = mutableStateOf(
        TomorrowEventsScreenState(
            events = emptyList(),
            isLoading = true,
            error = null
        )
    )
    val state: State<TomorrowEventsScreenState>
        get() = _state


    init {
        getEvents()
    }

    /* TODO we should make a view model out of this maybe? - but how to trigger events refetch then:
* - either in UI, afte
* */
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

            /* TODO maybe some flow here would be better - then we would only fetch all upcoming, and not today or tomorrow events (which dont have bookmark option on them */
            /* i dont know, this needs to be worked out */
            /* also, maybe it is overkill to load ALL events from db just because ONE SINGLE event got its isBookmarked field set */

            /* reload fresh events from db */
            handleGetEvents()
        }
    }

    private fun getEvents() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        /* TODO missing error handling */
        viewModelScope.launch {
            handleGetEvents()
        }
    }


    private suspend fun handleGetEvents() {
        val todayEvents = getTomorrowEventsUseCase()

        val updatedState = _state.value.copy(
            events = todayEvents,
            isLoading = false,
            error = null,
        )

        _state.value = updatedState
    }
}