package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

data class EventScreenState(
    val event: EventModel?,
    val isLoading: Boolean,
    val error: String?,
)