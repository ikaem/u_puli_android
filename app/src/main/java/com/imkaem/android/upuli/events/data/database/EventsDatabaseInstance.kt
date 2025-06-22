package com.imkaem.android.upuli.events.data.database

import android.content.Context
import android.provider.CalendarContract
import androidx.room.Room
import androidx.room.RoomDatabase

object EventsRoomInstance {

    private const val DATABASE_NAME = "events_database"

    fun dao(context: Context): EventsDao {
        val db = Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            DATABASE_NAME,
        )
//            .addMigrations()
            .build()

        val dao = db.dao

        return dao
    }
}