package com.imkaem.android.upuli.events.data.remote

data class EventsResponseRemoteEntity(
    val ok: Boolean,
    val message: String,
    val data: EventsResponseDataRemoteEntity
)

/* TODO how to make this a private, so it cannot be accessed outside of this file */
data class EventsResponseDataRemoteEntity(
    val events: List<EventRemoteEntity>
)