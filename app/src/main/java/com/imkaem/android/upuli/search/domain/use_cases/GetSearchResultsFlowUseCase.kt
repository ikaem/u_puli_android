package com.imkaem.android.upuli.search.domain.use_cases

import com.imkaem.android.upuli.search.data.SearchRepository
import com.imkaem.android.upuli.search.domain.models.SearchModel
import com.imkaem.android.upuli.search.utils.values.SearchResultIdsValue
import kotlinx.coroutines.flow.Flow

class GetSearchResultsFlowUseCase(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(idsValue: SearchResultIdsValue): Flow<SearchModel> {
        return searchRepository.getSearchResultFlow(idsValue)
    }
}