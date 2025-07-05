package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository

/* TODO this should not be used, as it will overwrite */
/* TODO we should add logic here for it only to load events if we are offline */
class LoadEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend operator fun invoke(id: Int): Unit {
        eventsRepository.loadEvent(id)
    }
}