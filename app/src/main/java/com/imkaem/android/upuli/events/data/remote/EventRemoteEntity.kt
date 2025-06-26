package com.imkaem.android.upuli.events.data.remote

import com.google.gson.annotations.SerializedName

data class EventRemoteEntity(
    val id: Int,
    val title: String,
    /* TODO this is in milliseconds */
    @SerializedName("date")
    val dateInMilliseconds: Long,
    val location: String,
    val url: String,
    val imageUrl: String,
    val description: String,
)
