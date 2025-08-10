package com.imkaem.android.upuli.events.domain.use_cases

import androidx.compose.ui.geometry.Offset
import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.GetEventsFilter
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class GetHomeScreenEventsFlowUseCase(
    private val eventsRepository: EventsRepository,
) {

    operator fun invoke(
        /* TODO eventu aly, we will get page here i guess? */
//        today: LocalDate,
//        tomorrow: LocalDate,
        today: LocalDateTime,
        tomorrow: LocalDateTime,
    ): Flow<GetHomeScreenEventsFlowUseCaseResultValue> {
//        val startOfToday = today.atStartOfDay(ZoneId.systemDefault())
//        val startOfTodayInMilliseconds = startOfToday.toInstant().toEpochMilli()

//        val startOfTomorrow = tomorrow.atStartOfDay(ZoneId.systemDefault())
//        val startOfTomorrowInMilliseconds = startOfTomorrow.toInstant().toEpochMilli()
//
//        val startOfDayAfterTomorrow = today.plusDays(2).atStartOfDay(ZoneId.systemDefault())
//        val startOfDayAfterTomorrowInMilliseconds = startOfDayAfterTomorrow.toInstant().toEpochMilli()

//        val todayInMilliseconds = today.toInstant(ZoneOffset.UTC).toEpochMilli()
        val todayInMilliseconds = today.toLocalDate().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
        /* we want to make sure we use start of day tomorrow */
        val tomorrowInMilliseconds = tomorrow.toLocalDate().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
        val dayAfterTomorrowInMilliseconds = tomorrow.plusDays(1).toLocalDate().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

        val filter = GetEventsFilter(
            fromDateMilliseconds = todayInMilliseconds,
            toDateMilliseconds = null // No end date, we want all upcoming events
        )

        val allUpcomingEventsFlow: Flow<List<EventModel>> = eventsRepository.getEventsFlow(filter = filter)

        val resultFlow: Flow<GetHomeScreenEventsFlowUseCaseResultValue> = allUpcomingEventsFlow.map { events ->
            val todayEvents = mutableListOf<EventModel>();
            val tomorrowEvents = mutableListOf<EventModel>();

            events.forEach {
                /* TODO this is probalbly long when converting zones and so on - but lets keep it like this for now */
                val eventDateInMilliseconds = it.dateTime.toInstant(ZoneOffset.UTC).toEpochMilli()

                val isToday = eventDateInMilliseconds >= todayInMilliseconds && eventDateInMilliseconds < tomorrowInMilliseconds
                val isTomorrow = eventDateInMilliseconds >= tomorrowInMilliseconds && eventDateInMilliseconds < dayAfterTomorrowInMilliseconds

                if (isToday) {
                    todayEvents.add(it)
                } else if (isTomorrow) {
                    tomorrowEvents.add(it)
                }
            }

            val result = GetHomeScreenEventsFlowUseCaseResultValue(
                todayEvents = todayEvents,
                tomorrowEvents = tomorrowEvents,
                allUpcomingEvents = events
            )

            result
        }

        return resultFlow
    }


}

/* TODO move to values */
data class GetHomeScreenEventsFlowUseCaseResultValue(
    val todayEvents: List<EventModel>,
    val tomorrowEvents: List<EventModel>,
    val allUpcomingEvents: List<EventModel>,
)

/* we need some class that we can return from here */

/* ok, so maybe we can do this:
* 1. get all events from today on
* 2. in this use case, find today events
* 3. in this use case, find tomorrow events
* 4. and then put all others there too
*
* */

/* ok, so the flow
* 1. initially, call use case to get all events for page 1
* 2. then, we can trigger same function to get next page - and we would append events to the list
*
*
*
* lets create use repository function to get all events from today on
* */
