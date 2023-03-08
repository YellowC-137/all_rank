package yellowc.app.allrank.data.remote.response.foreign_response

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val mbid: String,
    val name: String,
    val url: String
)