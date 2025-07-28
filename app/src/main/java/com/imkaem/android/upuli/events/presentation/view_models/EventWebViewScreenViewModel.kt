package com.imkaem.android.upuli.events.presentation.view_models

import android.media.metrics.Event
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch

class EventWebViewScreenViewModel(
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    /* TODO we will use regular get event here, because there is no good justification to use flow */
    val getEventUseCase = DummyDI.getEventUseCase

    private val _state = mutableStateOf<EventWebViewScreenState>(EventWebViewScreenState.initial())
    val state: State<EventWebViewScreenState>
        get() = _state;

    init {
        getUrl()
    }


    private fun getUrl() {
        /* TODO but if we throw here, what will catch the exception? */
        val id = stateHandle.get<Int>("event_id")
            ?: throw IllegalArgumentException("Event ID not found in state handle")

        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            handleGetEventUrl(id)
        }
    }

    private suspend fun handleGetEventUrl(id: Int) {
        val event = getEventUseCase(id)

        val updatedState = _state.value.copy(
            url = event?.url,
//            url = null,
            isLoading = false,
            error = null,
        )

        _state.value = updatedState;
    }
}

/* TODO move to its own file */
data class EventWebViewScreenState(
    val url: String?,
    val isLoading: Boolean,
    val error: String? = null,
) {
    companion object {
        /* TODO this is cool - make initial static averywher */
        fun initial() = EventWebViewScreenState(
            url = null,
            isLoading = true,
            error = null
        )
    }
}