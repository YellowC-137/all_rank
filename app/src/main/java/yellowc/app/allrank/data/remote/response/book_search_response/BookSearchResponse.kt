package yellowc.app.allrank.data.remote.response.book_search_response

import kotlinx.serialization.Serializable

@Serializable
data class BookSearchResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)