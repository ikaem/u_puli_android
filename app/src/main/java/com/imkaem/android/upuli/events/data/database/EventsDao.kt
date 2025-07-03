package com.imkaem.android.upuli.events.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.imkaem.android.upuli.events.data.local.EventLocalEntity
import com.imkaem.android.upuli.events.data.local.EventLocalEntityPartialIsBookmarked

@Dao
interface EventsDao {

    /* TODO in future, some pagination will need to be done */
    @Query("SELECT * FROM events")
    suspend fun getAll(): List<EventLocalEntity>

    @Query("SELECT * FROM events WHERE is_bookmarked = 1 AND date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllBookmarkedFromInclusive(fromMillisecondsInclusive: Long): List<EventLocalEntity>

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

    /* NOTE: we dont necessarily need to use partial entity here */
    @Update(entity = EventLocalEntity::class)
    suspend fun updateIsBookmarked(partialEvent: EventLocalEntityPartialIsBookmarked)

}