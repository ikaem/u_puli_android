package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.LocalDate
import java.time.ZoneId

class GetTodayEventsUseCase(
    private val eventsRepository: EventsRepository,
) {


    suspend operator fun invoke(): List<EventModel> {
        /* TODO iam really not sure about which zone i should use - maybe this? - ZoneOffset.UTC */
        val startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
        val startOfNextDay = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault())

        val startOfTodayInMilliseconds = startOfDay.toInstant().toEpochMilli()
        val startOfTomorrowInMilliseconds = startOfNextDay.toInstant().toEpochMilli()

        val filter = GetEventsFilter(
            fromDateMilliseconds = startOfTodayInMilliseconds,
            toDateMilliseconds = startOfTomorrowInMilliseconds,
        )

        val events = eventsRepository.getEvents(
            filter = filter,
        )

        return events
    }
}