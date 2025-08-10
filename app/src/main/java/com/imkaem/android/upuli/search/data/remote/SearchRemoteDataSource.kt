package com.imkaem.android.upuli.search.data.remote

import android.util.Log
import com.imkaem.android.upuli.core.data.api_service.UPuliApiService

class SearchRemoteDataSource(
    private val apiService: UPuliApiService,
    /* TODO will need to pass ui disčlatcher here, to be used wthen fetching data */
) {

    suspend fun search(query: String): SearchRemoteEntity {
        val response = apiService.search(query)

//        Log.d("SearchRemoteDataSource", "Search response: $response")
        if (!response.ok) {
            throw Exception("Error searching for query '$query': ${response.message}")
        }

        val events = response.data.result.events

        val searchRemoteEntity = SearchRemoteEntity(
            events = events
//            events = emptyList()
        )

        return searchRemoteEntity
    }
}