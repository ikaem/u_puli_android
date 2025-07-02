package com.imkaem.android.upuli.events.domain.use_cases

import com.imkaem.android.upuli.events.data.EventsRepository

class UpdateEventIsBookmarkedUseCase(
    private val eventsRepository: EventsRepository
) {
    suspend operator fun invoke(
        id: Int,
        oldIsBookmarked: Boolean,
    ) {
        eventsRepository.updateEventIsBookmarked(
            eventId = id,
            isBookmarked = !oldIsBookmarked,
        )
    }
}