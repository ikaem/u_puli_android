package com.imkaem.android.upuli.events.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.core.utils.di.DummyDI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class BookmarkedEventsScreenViewModel : ViewModel() {
//    val getBookmarkedEventsFromTodayUseCase = DummyDI.getBookmarkedEventsFromDateUseCase

    val getBookmarkedEventsFromDateFlowUseCase = DummyDI.getBookmarkedEventsFromDateFlowUseCase
    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase


    /* TODO not sure if this should be here */
    /* TODO this should eventually be converted to state object */
    private val todayDate: ZonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault())

    /* TODO not sure what is this supposed to be used for */
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
    val fromDateString: String
        get() =
            todayDate.format(dateFormatter)


    private val _state = MutableStateFlow<BookmarkedEventsScreenState>(
        BookmarkedEventsScreenState(
            bookmarkedEvents = emptyList(),
            isLoading = true,
            error = null,
            /* TODO maybe in future we hold here state about todays date, or some other filed? */
            /* TODO, or we have another view model? i dont know... */
        )
    )

    val state: StateFlow<BookmarkedEventsScreenState>
        get() = _state

    init {
        handleGenerateState()
    }


    private fun handleGenerateState() {
        /* TODO some error handler, and explicit IO dispatcher should be passed in */
        viewModelScope.launch {
            getStateFromEventsFlow()
        }
    }


    private suspend fun getStateFromEventsFlow() {
        getBookmarkedEventsFromDateFlowUseCase(
            startOfDateInMilliseconds = todayDate.toInstant().toEpochMilli()
        ).collect { events ->
            val newState = BookmarkedEventsScreenState(
                bookmarkedEvents = events,
                isLoading = false,
                error = null,
            )

            _state.update { newState }
        }
    }

    /* TODO we should make a view model out of this maybe? - but how to trigger events refetch then:
    * - either in UI, afte
    * */
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
//            handleGetEvents()
        }
    }

    /* TODO old implementation when no flow */

//
//    private val _state = mutableStateOf(
//        BookmarkedEventsScreenState(
//            bookmarkedEvents = emptyList(),
//            isLoading = true,
//            error = null,
////            fromDateString = "" // This can be dynamic based on the current date
//        )
//    )
//    val state: State<BookmarkedEventsScreenState>
//        get() = _state

//    init {
//        getEvents()
//    }


//    private fun getEvents() {
//        /* TODO some error handler, and explicit IO dispatcher should be passed in */
//        /* TODO missing error handling */
//        viewModelScope.launch {
////            handleLoadEvents()
//
//            handleGetEvents()
//        }
//    }
//
//
//    private suspend fun handleLoadEvents() {
//        /* no implementation yet */
//    }
//
//    private suspend fun handleGetEvents() {
//        val bookmarkedEvents = getBookmarkedEventsFromTodayUseCase(
//            startOfDateInMilliseconds = todayDate.toInstant().toEpochMilli()
//        )
//
//        val updatedState = _state.value.copy(
//            bookmarkedEvents = bookmarkedEvents,
//            isLoading = false,
//            error = null
//        )
//
//        _state.value = updatedState
//    }
}