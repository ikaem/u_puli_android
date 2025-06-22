package com.imkaem.android.upuli.events.data.remote

data class EventResponseRemoteEntity(
    val ok: Boolean,
    val message: String,
    val data: EventResponseDataRemoteEntity?
)

data class EventResponseDataRemoteEntity(
    val event: EventRemoteEntity
)
