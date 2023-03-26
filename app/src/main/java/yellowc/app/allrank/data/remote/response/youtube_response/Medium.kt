package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class Medium(
    val height: Int,
    val url: String,
    val width: Int
)