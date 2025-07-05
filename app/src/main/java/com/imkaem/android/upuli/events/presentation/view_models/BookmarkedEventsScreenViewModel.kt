package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class BookmarkedEventsScreenViewModel : ViewModel() {
    val getBookmarkedEventsFromTodayUseCase = DummyDI.getBookmarkedEventsFromDateUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase


    /* TODO not sure if this should be here */
    /* TODO this should eventually be converted to state object */
    private val todayDate: ZonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
    /* */


    val fromDateString: String
        get() =
            todayDate.format(dateFormatter)

    private val _state = mutableStateOf(
        BookmarkedEventsScreenState(
            bookmarkedEvents = emptyList(),
            isLoading = true,
            error = null,
            fromDateString = "" // This can be dynamic based on the current date
        )
    )
    val state: State<BookmarkedEventsScreenState>
        get() = _state

    init {
        getEvents()
    }

    fun onToggleEventIsBookmarked(
        id: Int,
    ) {

        val event = state.value.bookmarkedEvents.firstOrNull { it ->
            it.id == id
        }

        if (event == null) {
            /* TODO maybe some error state, so a toast can be shown to indicate that the event was not found */
            return
        }

        /* TODO  this needs some error handler, and IO dispatcher */
        viewModelScope.launch {
            updateEventIsBookmarkedUseCase(
                id = event.id,
                oldIsBookmarked = event.isBookmarked
            )

            /* TODO maybe some flow here would be better - then we would only fetch all upcoming, and not today or tomorrow events (which dont have bookmark option on them */
            /* i dont know, this needs to be worked out */
            /* also, maybe it is overkill to load ALL events from db just because ONE SINGLE event got its isBookmarked field set */

            /* reload fresh events from db */
            handleGetEvents()
        }
    }

    private fun getEvents() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        /* TODO missing error handling */
        viewModelScope.launch {
            handleLoadEvents()

            handleGetEvents()
        }

    }


    private suspend fun handleLoadEvents() {
        /* no implementation yet */
    }

    private suspend fun handleGetEvents() {
        val bookmarkedEvents = getBookmarkedEventsFromTodayUseCase(
            startOfDateInMilliseconds = todayDate.toInstant().toEpochMilli()
        )

        val updatedState = _state.value.copy(
            bookmarkedEvents = bookmarkedEvents,
            isLoading = false,
            error = null
        )

        _state.value = updatedState
    }
}