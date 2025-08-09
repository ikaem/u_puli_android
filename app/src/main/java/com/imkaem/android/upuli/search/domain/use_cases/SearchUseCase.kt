package com.imkaem.android.upuli.search.domain.use_cases

import com.imkaem.android.upuli.search.data.remote.SearchRepository
import com.imkaem.android.upuli.search.domain.models.SearchModel

class SearchUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): SearchModel {
        val searchModel = searchRepository.search(query)

        return searchModel
    }
}