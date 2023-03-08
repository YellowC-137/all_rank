package yellowc.app.allrank.data.remote.response.foreign_response

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val attr: Attr,
    val track: List<Track>
)