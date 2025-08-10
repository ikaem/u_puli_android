package com.imkaem.android.upuli.search.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

data class SearchScreenState(
    val events: List<EventModel>,
    val searchQuery: String,
    val isLoading: Boolean,
    val error: String? = null
)
