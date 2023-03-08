package yellowc.app.allrank.data.remote.response.foreign_response

import kotlinx.serialization.Serializable

@Serializable
data class Streamable(
    val text: String,
    val fulltrack: String
)