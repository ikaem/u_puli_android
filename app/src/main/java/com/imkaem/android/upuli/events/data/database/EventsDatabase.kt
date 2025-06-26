package com.imkaem.android.upuli.events.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.imkaem.android.upuli.events.data.local.EventLocalEntity

@Database(
    entities = [EventLocalEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class EventsDatabase : RoomDatabase() {
    abstract val dao: EventsDao
}

/* migrations - maybe at some point move to a different file */
val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE events ADD COLUMN url TEXT NOT NULL DEFAULT ''"
        )
        db.execSQL(
            "ALTER TABLE events ADD COLUMN description TEXT NOT NULL DEFAULT ''"
        )
        db.execSQL(
            "ALTER TABLE events ADD COLUMN image_url TEXT NOT NULL DEFAULT ''"
        )
    }

}