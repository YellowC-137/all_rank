package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Trackmatches(
    val track: List<Track>
)