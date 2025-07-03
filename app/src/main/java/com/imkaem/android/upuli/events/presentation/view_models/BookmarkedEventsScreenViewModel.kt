package com.imkaem.android.upuli.events.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class BookmarkedEventsScreenViewModel : ViewModel() {

    val getBookmarkedEventsFromTodayUseCase = DummyDI.getBookmarkedEventsFromDateUseCase

    /* TODO not sure if this should be here */

    val todayDate = LocalDate.now().atStartOfDay(ZoneId.systemDefault())

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