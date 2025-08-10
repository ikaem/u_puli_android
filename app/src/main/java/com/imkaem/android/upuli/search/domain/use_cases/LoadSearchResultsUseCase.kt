package com.imkaem.android.upuli.search.domain.use_cases

import com.imkaem.android.upuli.search.data.SearchRepository
import com.imkaem.android.upuli.search.utils.values.SearchResultIdsValue

class LoadSearchResultsUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): SearchResultIdsValue {
        val searchModel = searchRepository.loadSearchResult(query)

        return searchModel
    }
}