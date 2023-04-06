package yellowc.app.allrank.data.remote.response.game_search_response

import kotlinx.serialization.Serializable

@Serializable
data class ImageTag(
    val api_detail_url: String,
    val name: String,
    val total: Int
)