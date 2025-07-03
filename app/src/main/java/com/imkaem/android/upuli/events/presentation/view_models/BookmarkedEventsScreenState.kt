package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

data class BookmarkedEventsScreenState(
    val bookmarkedEvents: List<EventModel>,
    val isLoading: Boolean = false,
    val error: String? = null,
    /* TODO not sure if this should be here */
    val fromDateString: String,
    /* TODO might need to add some more fields, like filters and so on...*/
)