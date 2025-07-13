package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneId

/* TODO apparently, use of LocalDateTime is discouraged for some reason - I do use it throughout the code */

class GetTodayEventsFlowUseCase(
    private val eventsRepository: EventsRepository
) {
    operator fun invoke(): Flow<List<EventModel>> {
        val startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
        val startOfTodayInMilliseconds = startOfToday.toInstant().toEpochMilli()

        val startOfTomorrow = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault())
        val startOfTomorrowInMilliseconds = startOfTomorrow.toInstant().toEpochMilli()

        val filter = GetEventsFilter(
            fromDateMilliseconds = startOfTodayInMilliseconds,
            toDateMilliseconds = startOfTomorrowInMilliseconds
        )

        val todayEventsFlow: Flow<List<EventModel>> =
            eventsRepository.getEventsFlow(filter = filter)

        return todayEventsFlow
    }


}