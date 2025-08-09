package com.imkaem.android.upuli.search.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.core.utils.di.DummyDI
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class SearchScreenViewModel : ViewModel() {

    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase
    /* TODO i guess this should privide search results flow */
    val searchUseCase = DummyDI.searchUseCase


    private val _state = MutableStateFlow<SearchScreenState>(
        SearchScreenState(
            events = emptyList<EventModel>(),
            /* NOTE - we are not setting it to true until search starts */
            searchQuery = "",
            isLoading = false,
            error = null
        )
    )

    val state: StateFlow<SearchScreenState>
        get() = _state

    fun onChangeSearchQuery(query: String) {
        val newState = _state.value.copy(
            searchQuery = query,
        )

        /* TODO not sure if this is better */
        _state.update { newState }
//        _state.value = newState
    }

    fun onSubmitSearchQuery() {
        /* TODO this should work like this:
        * we trigger search
        * api is pinged
        * we get events from api
        * we store events in db
        * we need flow to get events from db
        * - for this reason, we have another use case that can fetch specific events from db, and is returning a flow
        * - or we have the same use case that triggerers search, and that can return flow? somewhere aroound repository? i dont know if we need to close flow or something like that?
        * - or we should do it like we do with getting events:
        * 1. one use case to search and load searched results
        * 2. this use case eventually returns only list of found events and whatever else we can search
        * 3. we have another use case that accepts those lists of ids, and returns flow of events and whatever else we can search
        * */
        if (_state.value.isLoading) {
            /* TODO lets now allow submit new one while existing one is running
            * later, we will use:
            * - debounce, as we will submit search query on user input change
            * - cancel previous search requues
            * */
            return;
        }

        _state.update { it.copy(isLoading = true) }

        Log.d("SearchScreenViewModel", "onSubmitSearchQuery: ${_state.value.searchQuery}")


        viewModelScope.launch {

            /* this should be flow i guess? i dont know... */
            val searchResults = searchUseCase(_state.value.searchQuery)

            Log.d("SearchScreenViewModel", "onSubmitSearchQuery: searchResults: $searchResults")



            withContext(Dispatchers.Main) {
                _state.update { it.copy(isLoading = false) }
            }
        }


        /* do stuff */


    }


    /* TODO theoretically, we dont even need id here. but this is just so we keep the signature same in all models, in case we unify it all at some point*/
    fun onToggleEventIsBookmarked(id: Int) {

        val event = state.value.events.firstOrNull { it -> it.id == id }
        if (event == null) {
            return
        }

        viewModelScope.launch {
            updateEventIsBookmarkedUseCase(
                id = event.id,
                oldIsBookmarked = event.isBookmarked
            )
        }
    }
}