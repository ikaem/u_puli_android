package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository

/* TODO we should add logic here for it only to load events if we are offline */
class LoadEventsUseCase(
    private val eventsRepository: EventsRepository
) {

    /* this only loads events from remote into local database */
    suspend operator fun invoke(): Unit {
        eventsRepository.loadEvents()
    }
}