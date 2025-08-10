package com.imkaem.android.upuli.search.data.remote

import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity

data class SearchRemoteEntity(
    val events: List<EventRemoteEntity>
    /* TODO if search result contains enything else, it will be here */
)