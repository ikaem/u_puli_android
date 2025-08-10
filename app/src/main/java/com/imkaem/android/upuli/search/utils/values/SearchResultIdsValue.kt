package com.imkaem.android.upuli.search.utils.values

/* this is used by the LoadSearchResultsUseCase
* the use case retruns this, so we can then later pass ids to a flow use case, to collect specific events (and other daa) from db
* */
data class SearchResultIdsValue(
    val eventIds: List<Int>
    /* TODO if search result contains anything else, it will be here */
)
