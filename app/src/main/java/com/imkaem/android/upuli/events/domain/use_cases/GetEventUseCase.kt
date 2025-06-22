package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository
import com.imkaem.android.upuli.events.domain.models.EventModel

class GetEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend operator fun invoke(id: Int): EventModel? {
        val event = eventsRepository.getEvent(id)

        return event
    }
}