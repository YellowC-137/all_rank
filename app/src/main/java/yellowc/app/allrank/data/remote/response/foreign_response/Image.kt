package yellowc.app.allrank.data.remote.response.foreign_response

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val text: String,
    val size: String
)