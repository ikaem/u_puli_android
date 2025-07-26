package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow

class GetBookmarkedEventsFromDateFlowUseCase(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(
        startOfDateInMilliseconds: Long,
    ): Flow<List<EventModel>> {

        val flow = eventsRepository.getBookmarkedEventsFromInclusiveFlow(startOfDateInMilliseconds)

        return flow
    }
}