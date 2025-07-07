package com.imkaem.android.upuli.events.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class EventWebViewScreenViewModel(
    private val stateHandle: SavedStateHandle,
): ViewModel() {


    private fun getUrl() {
        /* TODO but if we throw here, what will catch the exception? */
        val id = stateHandle.get<Int>("event_id") ?: throw IllegalArgumentException("Event ID not found in state handle")
    }
}