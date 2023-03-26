package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class Default(
    val height: Int,
    val url: String,
    val width: Int
)