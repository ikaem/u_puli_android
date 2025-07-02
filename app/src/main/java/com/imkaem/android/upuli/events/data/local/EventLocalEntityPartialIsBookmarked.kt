package com.imkaem.android.upuli.events.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

/* NOTE: Maybe this could be called something like PartialIsBookmarked */
@Entity
data class EventLocalEntityPartialIsBookmarked(
    val id: Int,

    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean,
)