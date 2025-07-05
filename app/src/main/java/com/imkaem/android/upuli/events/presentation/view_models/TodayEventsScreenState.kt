package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

/* TOOD this is same as TomorrowEventsScreenState (and bookmarkedE ventsScreenState= - consider unifying */
data class TodayEventsScreenState(
    val events: List<EventModel>,
    val isLoading: Boolean,
    val error: String? = null
)
