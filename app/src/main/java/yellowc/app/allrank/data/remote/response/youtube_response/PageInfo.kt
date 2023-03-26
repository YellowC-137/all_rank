package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)