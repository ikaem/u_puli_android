package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneId

class GetTomorrowEventsFlowUseCase(private val eventsRepository: EventsRepository) {

    operator fun invoke(): Flow<List<EventModel>> {
        val startOfTomorrow = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault())
        val startOfTomorrowInMilliseconds = startOfTomorrow.toInstant().toEpochMilli()

        val startOfDayAfterTomorrow = LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault())
        val startOfDayAfterTomorrowInMilliseconds = startOfDayAfterTomorrow.toInstant().toEpochMilli()

        val filter = GetEventsFilter(
            fromDateMilliseconds = startOfTomorrowInMilliseconds,
            toDateMilliseconds = startOfDayAfterTomorrowInMilliseconds
        )

        val tomorrowEventsFlow = eventsRepository.getEventsFlow(filter = filter)

        return tomorrowEventsFlow
    }
}