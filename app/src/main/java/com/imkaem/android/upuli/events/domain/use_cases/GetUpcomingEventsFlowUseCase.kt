package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneId

// TODO lets leave this here for now, for reference
class GetUpcomingEventsFlowUseCase(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(): Flow<List<EventModel>> {

        /* TODO iam really not sure about which zone i should use - maybe this? - ZoneOffset.UTC */
        val startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
        val startOfTodayInMilliseconds = startOfToday.toInstant().toEpochMilli()

        val filter = GetEventsFilter(
            fromDateMilliseconds = startOfTodayInMilliseconds,
            toDateMilliseconds = null // No end date, we want all upcoming events
        )

        return eventsRepository.getEventsFlow(filter = filter)

    }
}