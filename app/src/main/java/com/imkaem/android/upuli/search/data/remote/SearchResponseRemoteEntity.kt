package com.imkaem.android.upuli.search.data.remote

import android.os.Message
import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity

data class SearchResponseRemoteEntity(
    val ok: Boolean,
    val message: String,
    val data: SearchResponseDataRemoteEntity
//    val data: Any
)

/* TODO how to make this a private, so it cannot be accessed outside of this file */
data class SearchResponseDataRemoteEntity(
    val result: SearchResponseDataResultRemoteEntity
)

data class SearchResponseDataResultRemoteEntity(
    val events: List<EventRemoteEntity>,
    /* TODO if we ever have any other search results, they will be listed here */
)