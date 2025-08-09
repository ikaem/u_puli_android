package com.imkaem.android.upuli.search.data.remote

import androidx.room.Query
import com.imkaem.android.upuli.search.domain.models.SearchModel

class SearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource
    /* TODO this will also accept events local data source, and possibly any other remote and local data source whose entities are fetched and stored */
) {


    suspend fun search(query: String): SearchModel {

        val remoteEntity = searchRemoteDataSource.search(query)

        /* TODO temp we dont send anything back to the view model */
        val searchModel = SearchModel(
            events = emptyList()
        )

        return searchModel
    }
}