package com.imkaem.android.upuli.search.presentation.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.core.utils.di.DummyDI
import com.imkaem.android.upuli.events.domain.models.EventModel
import com.imkaem.android.upuli.search.utils.values.SearchResultIdsValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreenViewModel : ViewModel() {

    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase

    /* TODO i guess this should privide search results flow */
    val loadSearchResultUseCase = DummyDI.loadSearchResultsUseCase
    val getSearchResultsFlowUseCase = DummyDI.getSearchResultsFlowUseCase


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


    var getSearchResultsJob: Job? = null




    init {

    }

    fun onChangeSearchQuery(query: String) {
        val newState = _state.value.copy(
            searchQuery = query,
        )

        /* TODO not sure if this is better */
        _state.update { newState }
//        _state.value = newState
    }

    fun onSubmitSearchQuery() {
        if (_state.value.isLoading) {
            /* TODO lets now allow submit new one while existing one is running
            * later, we will use:
            * - debounce, as we will submit search query on user input change
            * - cancel previous search requues
            * */
            return;
        }

        /* cancel previous stuff:
        * primarily previous flow
        * - but i guess it could be reused to cancel the http request too
        * - https://stackoverflow.com/a/64785132/9661910
        * */
        getSearchResultsJob?.cancel()


        Log.d("SearchScreenViewModel", "onSubmitSearchQuery: ${_state.value.searchQuery}")


        getSearchResultsJob = viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _state.update { it.copy(isLoading = true) }
            }

            val idsValue = loadSearchResults(_state.value.searchQuery)
            getStateFromSearchResultsFlow(idsValue)

        }


        /* do stuff */


    }

    private suspend fun loadSearchResults(query: String): SearchResultIdsValue {

        /* this should be flow i guess? i dont know... */
        val idsValue = loadSearchResultUseCase(query)

        Log.d("SearchScreenViewModel", "onSubmitSearchQuery: idsValue: $idsValue")

        return idsValue
    }

    private suspend fun getStateFromSearchResultsFlow(idsValue: SearchResultIdsValue) {
        getSearchResultsFlowUseCase(idsValue).collectLatest { searchModel ->

            val currentIdsValue = idsValue.toString()
            Log.d("SearchScreenViewModel", "getStateFromSearchResultsFlow: currentIdsValue: $currentIdsValue")

            val events = searchModel.events

            val newState = _state.value.copy(
                events = events,
                isLoading = false,
                error = null
            )

            _state.update {
                newState
            }
        }
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