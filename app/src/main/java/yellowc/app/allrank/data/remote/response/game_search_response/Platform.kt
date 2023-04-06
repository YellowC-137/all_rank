package yellowc.app.allrank.data.remote.response.game_search_response

import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    val abbreviation: String,
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)