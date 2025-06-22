package com.imkaem.android.upuli.events.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventLocalEntity(
    @PrimaryKey()
    val id: Int,

    val title: String,

    @ColumnInfo(name = "date_in_milliseconds")
    val dateInMilliseconds: Long,

    val location: String,
)
