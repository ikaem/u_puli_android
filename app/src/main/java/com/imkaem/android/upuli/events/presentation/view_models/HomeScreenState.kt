package com.imkaem.android.upuli.events.presentation.view_models

import com.imkaem.android.upuli.events.domain.models.EventModel

/* TODO not sure if this should be here */
data class HomeScreenState(
    val todayEventsState: HomeScreenDayState?,
    val tomorrowEventsState: HomeScreenDayState?,
    val allUpcomingEvents: List<EventModel>,
    val isLoading: Boolean,
    val error: String?,
    val selectedTabIndex: Int,
)

data class HomeScreenDayState(
    val featuredEvent: EventModel,
    val dayEventsCount: Int,
)