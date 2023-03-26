package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    val default: Default,
    val high: High,
    val medium: Medium
)