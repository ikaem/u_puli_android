package com.imkaem.android.upuli.core.data.database

import android.content.Context
import androidx.room.Room
import com.imkaem.android.upuli.events.data.database.EventsDao

object UPuliDatabaseInstance {

    private const val DATABASE_NAME = "events_database"

    fun dao(context: Context): EventsDao {
        val db = Room.databaseBuilder(
            context,
            UPuliDatabase::class.java,
            DATABASE_NAME,
        )
            .addMigrations(
                migration_1_2,
                migration_2_3,
            )
            .build()

        val dao = db.dao

        return dao
    }
}