package com.imkaem.android.upuli.search.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imkaem.android.upuli.events.data.di.DummyDI
import com.imkaem.android.upuli.events.domain.models.EventModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {

    val updateEventIsBookmarkedUseCase = DummyDI.updateEventIsBookmarkedUseCase


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
        if(_state.value.isLoading) {
            /* TODO lets now allow submit new one while existing one is running
            * later, we will use:
            * - debounce, as we will submit search query on user input change
            * - cancel previous search requues
            * */
            return;
        }

        _state.update { it.copy(isLoading = true) }

        Log.d("SearchScreenViewModel", "onSubmitSearchQuery: ${_state.value.searchQuery}")


        /* do stuff */


    }


    /* TODO theoretically, we dont even need id here. but this is just so we keep the signature same in all models, in case we unify it all at some point*/
    fun onToggleEventIsBookmarked(id: Int) {

        val event = state.value.events.firstOrNull { it -> it.id == id }
        if(event == null) {
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