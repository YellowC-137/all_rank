package yellowc.app.allrank.data.remote.response.movie_search_response

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)