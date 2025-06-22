package com.imkaem.android.upuli.events.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imkaem.android.upuli.events.data.local.EventLocalEntity

@Database(
    entities = [EventLocalEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class EventsDatabase: RoomDatabase() {
    abstract val dao: EventsDao
}