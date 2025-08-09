package com.imkaem.android.upuli.search.domain.models

import com.imkaem.android.upuli.events.domain.models.EventModel

data class SearchModel(
    val events: List<EventModel>
    /* TODO if search result contains anything else, it will be here */
)
