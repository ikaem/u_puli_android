package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

/* TODO not sure if this should be here */
data class EventsScreenState(
    val todayEventsState: EventsScreenDayState?,
    val tomorrowEventsState: EventsScreenDayState?,
    val allUpcomingEvents: List<EventModel>,
    val isLoading: Boolean,
    val error: String? = null,
)

data class EventsScreenDayState(
    val featuredEvent: EventModel,
    val dayEventsCount: Int,
)