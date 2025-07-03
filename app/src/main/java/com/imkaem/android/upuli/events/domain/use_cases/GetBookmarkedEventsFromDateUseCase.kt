package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.LocalDate
import java.time.ZoneId

class GetBookmarkedEventsFromDateUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend operator fun invoke(
        startOfDateInMilliseconds: Long,
    ): List<EventModel> {
        /* TODO: these zones ar an issue - here, and on the server too */
//        val startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
//        val startOfDayInMilliseconds = startOfDay.toInstant().toEpochMilli()

        val events = eventsRepository.getBookmarkedEventsFromInclusive(
            fromMillisecondsInclusive = startOfDateInMilliseconds
        )

        return events;
    }
}