package com.imkaem.android.upuli.events.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventLocalEntity(
    @PrimaryKey()
    /* TODO: shouldn't ids in db be Long? maybe int is enough, if we dont expect lot of events? */
    val id: Int,

    val title: String,

    @ColumnInfo(name = "date_in_milliseconds")
    val dateInMilliseconds: Long,

    val location: String,

    val url: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    val description: String,

    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean = false,
)
