package com.imkaem.android.upuli.events.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imkaem.android.upuli.events.data.local.EventLocalEntity

@Dao
interface EventsDao {

    /* TODO in future, some pagination will need to be done */
    @Query("SELECT * FROM events")
    suspend fun getAll(): List<EventLocalEntity>

    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllFromInclusive(fromMillisecondsInclusive: Long): List<EventLocalEntity>

    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive AND date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllFromInclusiveToExclusive(
        fromMillisecondsInclusive: Long,
        toMillisecondsExclusive: Long
    ): List<EventLocalEntity>

    @Query("SELECT * FROM events WHERE date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllToExclusive(
        toMillisecondsExclusive: Long
    ): List<EventLocalEntity>


    @Query("SELECT * FROM events WHERE id = :id")
    suspend fun getOne(id: Int): EventLocalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(events: List<EventLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(event: EventLocalEntity) {
        addAll(listOf(event))
    }

}