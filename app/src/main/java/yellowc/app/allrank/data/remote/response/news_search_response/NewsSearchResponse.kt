package yellowc.app.allrank.data.remote.response.news_search_response

import kotlinx.serialization.Serializable

@Serializable
data class NewsSearchResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)