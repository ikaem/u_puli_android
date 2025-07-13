package com.imkaem.android.upuli.events.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.imkaem.android.upuli.events.data.local.EventLocalEntity
import com.imkaem.android.upuli.events.data.local.EventLocalEntityPartialIsBookmarked
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {


    /* TODO in future, some pagination will need to be done */
    @Query("SELECT * FROM events ORDER BY date_in_milliseconds ASC")
    suspend fun getAll(): List<EventLocalEntity>


    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllFromInclusive(fromMillisecondsInclusive: Long): List<EventLocalEntity>




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


    @Update(entity = EventLocalEntity::class)
    suspend fun updateAllIsBookmarked(partialEvents: List<EventLocalEntityPartialIsBookmarked>)

    /* FLOWS - testing */

    /* NOTE: that is is not a suspend function */
    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    fun getAllFromInclusiveFlow(fromMillisecondsInclusive: Long): Flow<List<EventLocalEntity>>

    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive AND date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    fun getAllFromInclusiveToExclusiveFlow(
        fromMillisecondsInclusive: Long,
        toMillisecondsExclusive: Long,
    ): Flow<List<EventLocalEntity>>

    @Query("SELECT * FROM events WHERE date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    fun getAllToExclusiveFlow(toMillisecondsExclusive: Long): Flow<List<EventLocalEntity>>

    @Query("SELECT * FROM events ORDER BY date_in_milliseconds ASC")
    fun getAllFlow(): Flow<List<EventLocalEntity>>

    /* NOTE: This is only used to ensure that bookmarked events are not overwritten when fetch new events from remote. Hence, no need for this to be a flow */
    @Query("SELECT * FROM events WHERE is_bookmarked = 1 ORDER BY date_in_milliseconds ASC")
    suspend fun getAllBookmarked(): List<EventLocalEntity>


    @Query("SELECT * FROM events WHERE is_bookmarked = 1  AND date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    fun getAllBookmarkedFromInclusiveFlow(fromMillisecondsInclusive: Long): Flow<List<EventLocalEntity>>


    /* NOT USED */

    @Query("SELECT * FROM events WHERE date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllToExclusive(
        toMillisecondsExclusive: Long
    ): List<EventLocalEntity>
    @Query("SELECT * FROM events WHERE is_bookmarked = 1 AND date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllBookmarkedFromInclusive(fromMillisecondsInclusive: Long): List<EventLocalEntity>


    @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive AND date_in_milliseconds < :toMillisecondsExclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllFromInclusiveToExclusive(
        fromMillisecondsInclusive: Long,
        toMillisecondsExclusive: Long
    ): List<EventLocalEntity>


}

/*
*     @Query("SELECT * FROM events WHERE date_in_milliseconds >= :fromMillisecondsInclusive ORDER BY date_in_milliseconds ASC")
    suspend fun getAllFromInclusive(fromMillisecondsInclusive: Long): List<EventLocalEntity>
*
* */
