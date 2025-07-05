package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

/* TODO maybe state classes can go to utils/values/state folder - and then be named StateValue class*/
data class EventScreenState(
    val event: EventModel?,
    val isLoading: Boolean,
    val error: String?,
)