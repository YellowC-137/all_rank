package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)