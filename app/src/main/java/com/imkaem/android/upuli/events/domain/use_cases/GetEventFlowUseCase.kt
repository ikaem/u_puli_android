package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow

class GetEventFlowUseCase(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(id: Int): Flow<EventModel?> {
        val eventFlow = eventsRepository.getEventFlow(id)

        return eventFlow
    }
}