package yellowc.app.allrank.data.remote.response.game_search_response

import kotlinx.serialization.Serializable

@Serializable
data class GameSearchResponse(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: List<Result>,
    val status_code: Int,
    val version: String
)