package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val text: String,
    val size: String
)